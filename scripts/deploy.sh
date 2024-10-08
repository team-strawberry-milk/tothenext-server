REPOSITORY=/home/ubuntu/tothenext
cd $REPOSITORY

CURRENT_CONTAINER=$(sudo docker ps -aq --filter "name=tothenext-server")

if [ -z $CURRENT_CONTAINER ]
then
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
else
  echo "> kill  $CURRENT_CONTAINER"
  sudo docker rm -f $CURRENT_CONTAINER
  sudo docker rmi tothenext-image
fi

sudo docker build -t tothenext-image .
sudo docker run -d -p 80:8080 --name tothenext-server --network my tothenext-image