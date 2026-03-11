# Description

API Développée avec Spring Boot 3.5.11 en JAVA 17 pour le frontend :
https://github.com/OpenClassrooms-Student-Center/Mod-lisez-et-impl-mentez-le-back-end-en-utilisant-du-code-Java-maintenable

# Installation (Windows 11+)

## Prérequis

Assurez-vous d'avoir ou installez les prérequis suivants :
- Windows 11 (https://www.microsoft.com/fr-fr/software-download/windows11).
- Java OpenJDK 17 (https://learn.microsoft.com/fr-fr/java/openjdk/download).
- VirtualBox (https://www.virtualbox.org/wiki/Downloads).
- Vagrant (https://developer.hashicorp.com/vagrant/install).

## Base de données

1) Créez un répertoire pour la Base De Données (Exemple: C:\bdd).

2) Dans le répertoire créé au (1), créez un nouveau fichier nommé `Vagrantfile` (Sans extension), et mettez-y le code suivant :
```ruby
Vagrant.configure("2") do |config|
  config.vm.box = "debian/bookworm64"
  config.vm.box_version = "12.20250126.1"

  # (Optionnel) IP privée stable pour accéder facilement à MariaDB
  config.vm.network "private_network", ip: "192.168.56.10"

  # Expose MariaDB sur l'hôte
  config.vm.network "forwarded_port",
    guest: 3306,
    host: 3306,
    host_ip: "127.0.0.1",
    auto_correct: true

  # Provisioning
  config.vm.provision "shell", path: "provision.sh"
end
```

3) Toujours dans le répertoire créé au (1), crééz un nouveau fichier nommé `provision.sh`,
Mettez-y le code suivant (en remplissant les variables de la section CONFIGURATION) :
```bash
#!/usr/bin/env bash

#################
# CONFIGURATION #
#################

# Root password
rootpw="INSERT ROOT PASSWORD HERE"

# Rental Application parameters (database name, login, password)
rental_base="INSERT DB NAME HERE"
rental_user="INSERT DB USER HERE"
rental_pass="INSERT DB PASS HERE"

################
# INSTALLATION #
################

# Installation of the SGBD MariaDB
sudo apt-get update -y
sudo apt-get install -y mariadb-server mariadb-client
sudo systemctl enable --now mariadb

#################
# CONFIGURATION #
#################

# Configuration of MariaDB to listen on all network interfaces
sudo sed -i 's/^[[:space:]]*bind-address[[:space:]]*=.*/bind-address = 0.0.0.0/' /etc/mysql/mariadb.conf.d/50-server.cnf || true
sudo grep -q '^[[:space:]]*bind-address' /etc/mysql/mariadb.conf.d/50-server.cnf || echo "bind-address = 0.0.0.0" >> /etc/mysql/mariadb.conf.d/50-server.cnf
sudo systemctl restart mariadb

# Set the MariaDB root password
sudo mysql --protocol=socket -uroot <<SQL
ALTER USER 'root'@'localhost' IDENTIFIED VIA mysql_native_password USING PASSWORD('${rootpw}');
FLUSH PRIVILEGES;
SQL

# Set up the DataBase and User privileges for the Rental Application
sudo mysql -uroot -p"${rootpw}" <<SQL
CREATE DATABASE IF NOT EXISTS \`${rental_base}\` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER IF NOT EXISTS '${rental_user}'@'%' IDENTIFIED BY '${rental_pass}';

GRANT ALL PRIVILEGES ON \`${rental_base}\`.* TO '${rental_user}'@'%';

FLUSH PRIVILEGES;
SQL

# Set up the DataBase tables for the Rental Application from the schema
if [ -f /vagrant/script.sql ]
then
	sudo mysql -u"${rental_user}" -p"${rental_pass}" "${rental_base}" < /vagrant/script.sql
fi
```

4) Toujours dans le répertoire créé au (1), crééz un nouveau fichier nommé `script.sql`, et mettez-y le code suivant :
```sql
CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE `RENTALS` ADD FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);
```

## Variables d'environnement

Des variables d'environnement sont nécessaires au bon fonctionnement de l'API :
- DB_URL : jdbc:mariadb://localhost:3306/INSERT_DB_NAME_HERE
- DB_USER : INSERT_DB_USER_HERE
- DB_PASSWORD : INSERT_DB_PASSWORD_HERE
- JWT_SECRET : INSERT_MINIMAL_32_LENGTH_SECRET_HERE

# Démarrage de l'API

Vous devez démarrer les éléments dans cet ordre précis :
1) Base De Données
2) Spring Boot

## Base De Données

- Dans le dossier de la Base De Données, exécutez la commande : `vagrant up`.
- Attendez de récupérer la main sur l'invite de commandes.

## Spring Boot

- A la racine du projet, exécutez la commande : `./mvnw spring-boot:run`.

# Tester l'API

Vous pouvez tester l'API en fonctionnement, grâce à OpenAPI (Swagger UI) à l'URL suivante :
http://localhost:3001/swagger-ui/index.html