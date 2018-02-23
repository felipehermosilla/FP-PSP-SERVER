export EB_CLI_HOME=/var/lib/jenkins/.local/bin

WAR_DIR=$1
DEPLOY_REGION=$2

echo "DEPLOY REGION: "$DEPLOY_REGION

## Deploy a US
if [ "$DEPLOY_REGION" = 'US' ]; then
  #El eb-cli debe ser invocado desde el directorio de deployments
  cd $WAR_DIR
  #status
  $EB_CLI_HOME/eb status
  echo "Esta versión seria deployada ${DEPLOY_VERSION}"
  #Se invoca el deployment con la version"
  $EB_CLI_HOME/eb deploy -l "PSP Server $DEPLOY_VERSION $BUILD_ID - $GIT_COMMIT"
fi

if [ "$DEPLOY_REGION" = "UK" ]; then
  #El eb-cli debe ser invocado desde el directorio de deployments
  cd $WAR_DIR
  #status
  $EB_CLI_HOME/eb status
  echo "Esta versión seria deployada ${DEPLOY_VERSION}"
  #Se invoca el deployment con la version"
  $EB_CLI_HOME/eb deploy -l "PSP Server $DEPLOY_VERSION #$BUILD_ID - $GIT_COMMIT"
fi

if [ "$DEPLOY_REGION" = "DEMO" ]; then
#El eb-cli debe ser invocado desde el directorio de deployments
cd $WAR_DIR
#status
$EB_CLI_HOME/eb status
echo "Esta versión seria deployada ${DEPLOY_VERSION}"
#Se invoca el deployment con la version"
$EB_CLI_HOME/eb deploy -l "PSP Server $DEPLOY_VERSION #$BUILD_ID - $GIT_COMMIT"
fi
