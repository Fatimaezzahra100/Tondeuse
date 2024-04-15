# Tondeuse Application

Pour packager et déployer l'application Tondeuse :

## 1- Utilisation de Maven :
Si votre projet utilise Maven, vous pouvez utiliser la commande suivante pour construire votre projet et générer un fichier JAR exécutable :

mvn clean package

Cette commande compile votre projet, exécute les tests unitaires, crée un fichier JAR contenant les classes compilées et les dépendances, et le place dans le répertoire target du projet. Les dépendances Spring Boot doivent être configuré dans le pom.xml.

## 2- Déploiement :

Une fois le fichier JAR est généré, le projet peut être déployer dans un environnement cible. 

On peut utiliser un container Docker pour déployer l'application, via la création de conteneurs légers et portables pour l'application. 
 Ensuite déployer ces conteneurs sur n'importe quelle plateforme prenant en charge Docker, y compris les serveurs locaux et les environnements de développement. 

- Création d'un fichier Dockerfile 

### 3-Utilisation d'outils de déploiement automatisé :

On peut également utiliser des outils d'intégration continue et de déploiement continu (CI/CD) tels que Jenkins, GitLab CI/CD, etc. pour automatiser le processus de déploiement de l'application.
Ces outils vous permettent de définir des pipelines de déploiement automatisés qui compilent, testent et déploient automatiquement l'application à chaque changement de code.

