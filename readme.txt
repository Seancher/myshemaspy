1. Run MySQL Servery
mysql.server start
2. Run in the command line
java -jar bin/schemaSpy.jar -t mysql -dp bin/mysql-connector-java-5.1.23-bin.jar -host localhost -db rbsall -o output/html_rbs_main/ -gv "/usr/local" -u root -X "(common_BRAND.Brand)" -norows -nologo -meta input/schemaspy.meta.xml

