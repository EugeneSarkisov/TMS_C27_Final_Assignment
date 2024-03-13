# TMS_C27_Final_Assignment

## Task: 
Create a programme for processing payment documents and providing financial statements. Access to the programme is by login and password.
The program receives the path to the folder as input, reads information from the files and compiles reports on them. The programme processes files only for a certain year. 
The programme implements various checks as well as saving logs to separate files (errors and general information).
When the programme finishes, all invalid files should be moved to a separate folder. Upon completion of the programme, all invalid files are moved to a separate folder.
The final statistics is uploaded to a separate file.

For **correct** operation of the programme it is necessary to correctly specify the correct path to the required documentation. The format of the document names must correspond to the specified format. Documents can be of 3 types: ***checks, invoices, orders.***File name formats: ****1. YYYYY_Electric_Bill_00 (checks). 2. INVOICE_00_YYYYYY (invoices). 3. YYYYY_ORDER_00 (orders).****

The program contains:
- "FileProcessService" class that prompts the user for login information and folder path information.

- "AuthorisationService" class that gets information from "StorageMock", compares it with the console input and according to the result (matched / not matched) executes the appropriate logic. Session passes the returned item.

- "Parsers" classes contain logic for implementing parsers that allow to verify files, get the value and get the sum of bills of all documents. Allow to verify necessary files and remove garbage order files to garbage package.

- "Logger" class is created to collect information about the work process. This information is recorded in a separate document. Logs contain the date and time of the operation.
- "ReportGenerator" class is created to create a report based on the information passed from parser classes.

//- The remaining classes contain logic for:
//for processing and correctly displaying exceptions,errors and runtime logs,
//for encrypting user data,
//and for checking the validity of files and folders.
