echo "******************************************"
echo "************Running on Chrome*************"
echo "******************************************"
mvn clean verify -Dbrowser=chrome

echo "******************************************"
echo "************Running on Firefox************"
echo "******************************************"
mvn clean verify -Dbrowser=firefox

echo "******************************************************"
echo "************Running on Headless Browser***************"
echo "******************************************************"
mvn clean verify -Dbrowser=headless
