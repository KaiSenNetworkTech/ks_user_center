call mvn eclipse:clean
call mvn -U eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=false -Dwtpversion=1.5
@pause