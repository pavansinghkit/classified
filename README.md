# classified Java Project

Internship project for Atlas2.0

This project is totally based on classified problem (An Internal OLX kind of Solution for Employee. Buy or Sell). In which we have to import three different tables (User, classified_info and interest) and take args from command line and display data according to query args.

// In this part we will play around classified_info table 

Requirement:
1: To add a new classified (By Admin/ Active User)

2: To see all classified (all classified for Admin/ Only Active classified for User)

3: To update the existing classified (Only Active classified)

4: Conform below payment mode :
        a: NET_BANKING
        b:CREDIT_OR_DEBIT_CARD
        c:CASH
        
5: Post payment change the classified status as SOLS for select product in classified_info table.
Note : All above operations will be performed in classified_info table.

Main method will execute from class :ClassifiedApp 
step 1: User will be asked to choose the below service:
        A: createClassified
        B: updateClassified
        C: printClassifiedList
        
1) If user selects option A:

User will be asked to select category (from softlines, hardlines, consumables, media, others), price, Title, discription along with his/her userId. After that Connection will stablised with MySqlJDBC and new classifiedId (primary key) will be generated and classified will be added in classified_info table with default status NA, created_at (current time) and created_by(User_name) . Will get popup saying that classified has been added successfully, do you want to add more? y/n. If user will select y/Y, again same process will be initiated or if user select N/n, Application will terminate. The status will be changed after admin approvals. 
Post execution connect will be closed.

2) If user selects option B:

User will be asked to enter his/her user_name, Connection will stablised with MySqlJDBC. Will execute query if the entered user_name is having any ACTIVE classifiedId or not to update. If there is no classifiedId with entered name will get popup, "No classified found for user name : user_name". If there is any ACTIVE classifiedId present in table then it will show the all available classifiedId and will ask to select classifiedId you want to update. Post selecting classifiedId again User will be asked to select category (from softlines, hardlines, consumables, media, others), price, Title, discription. The selected classifiedId (primary key) will be updated in classified_info table with entered changed adding 2 new column Modified_at (modified time) and modified_by(midifier_name). After updating 1 classified we Will get popup saying that Your classified has been updated successfully, but you can see only if this is approved by Admin, do you want to add more? y/n. If user will select y/Y, again same process will be initiated or if user select N/n,  connect will be closed and Application will terminate.

3) If user selects option C:

Connection will stablised with MySqlJDBC and will fetch all data from classified_info table irrespective to the status and user. Post execution connect will be closed and application will be terminated.

Thanks, 
