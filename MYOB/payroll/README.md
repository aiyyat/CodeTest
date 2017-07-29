# PAYROLL
PayRoll is an app that helps to calculate the payroll of a person given his salary information.

## Running the application
Source code is available in github and can be cloned with the following command.

$ git clone https://github.com/technicalyorker/payroll.git

$ cd payroll

$ mvn -e clean package

this will create a folder named target. Build will also create a folder named lib with the appropriate dependancy jars.
currently it uses file as an input but its very easy to use any other sources like User Input etc. Since the code uses Writer as its parameter literally anything can be a source or destination to the System.

$ java -jar target/payroll.jar

Usage: payroll <fully qualified input file> <fully qualified output file>
Now run the following command:

$ java -jar target/payroll.jar "src/test/resources/problem_scenario.txt" "file.csv"

This will read the file problem_scenario.txt and generate the file.csv in the current folder. Now open this file with any CSV editor.

## Design Motivation
In ordered to demonstrate both the use of a library and otherwise we have followed two approaches here. CSV reading is defined by the contract named SalaryEntryReader.java. This defines a class readNext that returns a SalaryEntry or a record that represents a Salary of a person during a calendar month. Note the first line in the CSV must represent the heading.
Writer on the other hand uses opencsv, a CSV library.

Both Reader and Writer uses the java.io.Writer and java.io.Reader to abstract it away from any particular implementation of source.

## Test
All the main scenarios are covered by the test cases and can be found in the test folder.
