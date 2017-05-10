1. Run MySQL Server
mysql.server start
2. Folder input/ stores the following files:
	* menutree_dump.txt - menu tree table
	* tmscodes_dump.txt - TMS codes
	* tmsparams_dump.txt - TMS params
	* schemaspy_meta.xml - relations between RBS tables
3. Run in the command line
java -jar bin/schemaSpy.jar -t mysql -dp bin/mysql-connector-java-5.1.23-bin.jar -host localhost -db rbsall -o output/html_rbs_main/ -gv "/usr/local" -u root -X "(common_BRAND.Brand)" -norows -nologo -meta input/schemaspy.meta.xml

OPTIONS:
-gv pathToGraphviz: by default SchemaSpy expects the dot executable to be in the PATH environment variable. Use this option to explicitly specify where Graphviz is installed. http://www.graphviz.org/
