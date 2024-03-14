# TMS_C27_Final_Assignment

### Task: 

   Create a programme for _processing payment documents_ and _providing financial statements_. Access to the programme is by _login_ and _password_.
The program receives the _path to the folder_ as input, reads information from the files and compiles _reports_ on them. The programme processes files only for a certain year. 
The programme implements various checks as well as saving logs to separate files (errors and general information).
When the programme finishes, _all invalid files should be moved to a separate folder_. Upon completion of the programme, _all invalid files are moved to a separate folder_.
The final statistics is _uploaded to a separate file_.

   For **correct** operation of the programme it is necessary to correctly specify the correct path to the required documentation. The format of the document names must correspond to the specified format. Documents can be of 3 types: ***checks, invoices, orders.*** File name formats: ****1. YYYYY_Electric_Bill_00 (checks). 2. INVOICE_00_YYYYYY (invoices). 3. YYYYY_ORDER_00 (orders).****

 ### The program contains:
- `FileProcessService` class that prompts the user for login information and folder path information.

- `AuthorisationService` class that gets information from "StorageMock", compares it with the console input and according to the result (matched / not matched) executes the appropriate logic. Session passes the returned item.

- `Parsers` classes contain logic for implementing parsers that allow to verify files, get the value and get the sum of bills of all documents. Allow to verify necessary files and remove garbage order files to garbage package.

- `Logger` class is created to _collect_ information about the work process. Loggers are inserted into the programme and output the above information. This information is _recorded in a separate document_. Logs contain the _date and time_ of the operation.
  
- `ReportGenerator` class is created to create a _report_ based on the information passed from parser classes.
  
- `Custom Exception` classes contain an _exception pattern_ for _checks, invoices, orders_ folders that do not exist. Also contain a _pattern of exception_ for folder which does not exist and a pattern for empty folder exception.
  
- `Eencoder` class contains a method to _encode_ and _decode_ the user's _login_ and _password_. It also contains a method `addSalt` for encrypting data.

- `Validator` checking if folder _empty_ or _not exist_. Get the methods from  `DirectoryValidation` class. Which allow to check _is folder empty or is folder exist_.

