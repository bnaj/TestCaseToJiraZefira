#
# TestCaseToJiraZefira



Hello I&#39;m automate tester and this project become to life because i neead some tool to export test scenario from docx files to Zephira. I&#39;m not a programmer and... I do not assume any liability for the operation of this program :) (Don&#39;t worry work fine for me :D)

How do you can see, my skills in English are on the same level. But i do what i can to show you how it work.



Intel i9-7900X or equivalent

1. GTX Titan Z
2. 16Giga of ram
3. Vr helm (no matter what manufacturer) you need is to read cmd logs......

I joked, you need:

1. java
2. If you read this i suppose you have account on Jira.
3. Your must have checked options in jira configuration which Enabling the Remote API.
4. Link how do this: [https://confluence.atlassian.com/doc/enabling-the-remote-api-150460.html](https://confluence.atlassian.com/doc/enabling-the-remote-api-150460.html)
5. You must have active Zephyr and Zapi. (You can bought it on jira market). If you pay for Zephyr you must pay also for Zapi!!! its terrible.

Application should work on Linux and Windows (using Linux is better;)).

In this card i tell you how start this program.

1. You can download repo and start this in some ide.
2. You can download jar and open it in cmd or Linux terminal.

For Windows users go to start enter cmd, hit comandline. Go to folder where you download TestCaseToJiraZefira.jar. In comand line enter java -jar TestCaseToJiraZefira.jar.

file you can here find just download it.<<<<<<<<<<<<<<<<<<<



When you start program you see something like:


#
# First window

# What we have on first screan:
![alt text](https://github.com/bnaj/TestCaseToJiraZefira/blob/image/first.png)

1. In first field you must suplie address to your jira.


In this case i provide: [http://192.168.0.116:8080](http://192.168.0.116:8080/)

But in other situation it an be http://jira.yourCompany
![alt text](https://github.com/bnaj/TestCaseToJiraZefira/blob/image/jira%20address.png)


2. Project key.

When you login to your jira and go to project tab you can avalible projects.

Project key is in brackets like projectJira(PROJ)
![alt text](https://github.com/bnaj/TestCaseToJiraZefira/blob/image/proj.png)



3. Here you enter your login.

4. Here you enter your password.

5. When you fill all field click this button.

#
# Second screan

#
![alt text](https://github.com/bnaj/TestCaseToJiraZefira/blob/image/second%20screan.png)
1. Here you enter row of table where is your summary.

2. Here you enter cell number of in row from field 1 ehere is your summarry.

3. Here you enter row of table where is your description.

4. Here you enter cell number of in row from field 1 ehere is your description.

5.  Here you enter row where is test steps (is optional in some time).

6. Check this box if you provide test data to zephyr.

7. When you fill all field click this button.

Ok What row what cell?!! I show you below.

Ok here we have simple test scenario…

How you can see **summary** is in first row then you enter 1 in **first field** but text what we need is in the second cell, in the **second field** you enter 2.

Simple.

Description, row of **description** is second then we enter 2 in **third field** text is in second cell then we enter 2 int **fourth field .**

In this configuration when program starts take summary and descripton and start load steps.

| Summary: | Firs TS |
| --- | --- |
| Description: | Description to first TS |
| Step 1 | This is  step 1 s1 | This is resoult of  1 s1 |

Ok what with **field number 5**?

Ok in this case we have one more row after steps.

Now its time to enter row when test steps start. In this case it be 4. Enter 4 to **5 field**.

When program starts load summary and description leave my company id and start load test steps.

| Summary: | Firs TS |
| --- | --- |
| Description: | Description to first TS |
| My company id | Namme of company |
| Step 1 | This is  step 1 s1 | This is resoult of  1 s1 |

**Number, 6 Checkbox** if your test scenario loks like:

| Summary: | Firs TS |
| --- | --- |
| Description: | Description to first TS |
| Step 1 | This is  step 1 s1 | This data of  1 s1 | This is resoult of  1 s1 |
| Step 2 | This is  step 2 s1 | This data of  2 s1 | This is resoult of  2 s1 |

How you can see in this scenario we provide test data to Zephyr. If you Check the box program load

**test step** , **test data** and **test resoult**.



#
# Last screan

#
![alt text](https://github.com/bnaj/TestCaseToJiraZefira/blob/image/last.png)

1. When you click this button you can select test scenarios to load (all scenarios must be arange in the same convention).

2. This button start program and take your test to Jira (I&#39; have hope :D).

3. You can delete **test scenario** when you click this button, first check the box next to **test scenario**.
