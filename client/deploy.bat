call mvn package
call mvn deploy:deploy-file -DgroupId=com.kaisen.usercenter -DartifactId=usercenter.client -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dfile=target/usercenter.client-0.0.1-SNAPSHOT.jar -Durl=http://192.168.2.210:10001/nexus/content/repositories/snapshots/ -DrepositoryId=snapshots
@pause