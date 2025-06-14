# Variables
$EC2_USER = "ec2-user"
$EC2_IP = "ec2-35-181-114-26.eu-west-3.compute.amazonaws.com"
$KEY_PATH = "C:\Phuc\STUDI\ECF2025\KEY-EC2-DO.pem"
$APP_NAME = "ecf2025-0.0.1-SNAPSHOT.jar"
$SOURCE_PATH = "C:\Phuc\STUDI\ECF2025\ecf2025"

Write-Host "Demarrage du pipeline CI/CD..."

# Etape 1 : Build du projet
Write-Host "Compilation du projet..."
Set-Location -Path $SOURCE_PATH
mvn clean package -DskipTests
if ($LASTEXITCODE -ne 0) { Write-Host "Echec du build"; exit 1 }

# Etape 2 : Executer les tests
Write-Host "Execution des tests..."
mvn test
if ($LASTEXITCODE -ne 0) { Write-Host "Echec des tests"; exit 1 }

# Verification si le fichier JAR est genere
if (!(Test-Path "$SOURCE_PATH\target\$APP_NAME")) {
    Write-Host "Erreur : le fichier JAR n'a pas ete genere."
    exit 1
}

# Etape 3 : Transfert du fichier vers EC2
Write-Host "Transfert du fichier vers EC2..."
scp -i "$KEY_PATH" "$SOURCE_PATH\target\$APP_NAME" "$EC2_USER@${EC2_IP}:/home/ec2-user/${APP_NAME}"
if ($LASTEXITCODE -ne 0) { Write-Host "Echec du transfert"; exit 1 }

# Etape 4 : Deploiement et execution sur EC2
Write-Host "Arreter l'application en cours sur EC2..."
ssh -i "$KEY_PATH" $EC2_USER@$EC2_IP "pgrep -f '$APP_NAME' && pkill -f '$APP_NAME' || echo 'Aucune application en cours'"
Write-Host "Redemarrer l'application sur EC2..."
ssh -i "$KEY_PATH" $EC2_USER@$EC2_IP "nohup java -jar /home/ec2-user/'$APP_NAME' --server.address=0.0.0.0 --server.port=8081 > /home/ec2-user/'$APP_NAME'.log 2>&1 &"
Write-Host "Deploiement CI/CD termine avec succes !"