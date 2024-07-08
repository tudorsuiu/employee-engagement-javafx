<h2> © Credit </h2>
Everything except the icons used in the shop was designed and created by me.
<br>
<a href="https://www.flaticon.com/free-icon/merchandise_4047583?term=merchandise&page=1&position=1&origin=tag&related_id=4047583">Credit merchandise_icon</a>
<br>
<a href="https://www.vecteezy.com/png/1203943-mouse-pad-computer">Credit mousepad_icon</a>
<br>
<a href="https://www.flaticon.com/free-icon/mouse_10335248?term=mouse&page=1&position=15&origin=tag&related_id=10335248">Credit mouse_icon</a>
<br>
<a href="https://www.flaticon.com/free-icon/keyboard_2263611?term=keyboard&page=1&position=1&origin=tag&related_id=2263611">Credit keyboard_icon</a>
<br>
<a href="https://www.flaticon.com/free-icon/day-off_7174337?term=day+off&page=1&position=1&origin=search&related_id=7174337">Credit dayoff_icon</a>

<h2> 🔍 Requirement </h2>
The company "Blue" needs a software solution to increase employee engagement, so that they participate in more activities and consequently have a stronger sense of satisfaction. Studies show that a gamification concept would fit very well to solve the problem in an interactive way, providing users with a great experience.
<br>
For the creation of this project, I used <strong>JavaFX</strong> as the framework and <strong>PostgreSQL</strong> for data storage.
<br>

<h2> ℹ️ About </h2>
This application was created to help manage quests within the company Blue, including the departments of Software Development, Technical Support, Quality Assurance, Information Security, and Project Management.
<br>
<br>
The goal of this project is to motivate employees to improve their skills and performance by completing quests. Employees can choose to accept quests from their department and complete them to earn points. Once an employee finishes a quest and submits it, a manager from the respective department will validate the quest. If accepted, the corresponding points will be added to the employee's total accumulated points. Each employee can also create quests for their department colleagues, as long as they have the necessary points to reward them.
<br>
<br>
If a manager does not validate an employee's quest submission, the employee will have the opportunity to try submitting the quest again. Each employee can create a quest as long as they have enough points to offer as a reward for another employee completing the quest.
<br>
<br>
Each employee will have a badge based on the number of points accumulated. With the points collected, employees can access the internal store to purchase items or services.
<br>
<br>
Department managers have access to the ranking page, where they can view the performance of employees in their department, sorted by the number of points accumulated and other performance metrics within the company.
<br>
<br>
The application was designed to encourage Blue company employees to improve their skills and grow within the company.
<br>

<h2> 🛠️ Functionalities </h2>
✅ Users can earn tokens/points and badges by participating in and completing quests.
<br>
<strong> --> </strong> I implemented this functionality as follows: employees can earn points by completing quests, which must be validated by the manager(s) of their respective department. Based on the accumulated points, they will receive badges.
<br>
<br>
✅ Users must be identified, and their related data (scores, names, etc.) must be saved. 
<br>
<strong> --> </strong> To save the data for users (Admin, Employee) as well as other classes used (Quest), I used <strong>PostgreSQL</strong>.
<br>
<br>
✅ Quests can be proposed by anyone, provided they have enough tokens/points to reward potential winners/participants.
<br>
<strong> --> </strong> Quests can only be created by employees based on the points they have and only within their respective department. If an employee tries to create a quest and does not have the necessary points, an error with a specific message will be displayed.
<br>
<br>
✅ We need a way for those who have chosen a quest to easily demonstrate its completion. 
<br>
<strong> --> </strong> To accomplish this task, I added an attribute "link" to the Quest class, which will initially be null. When an "Employee" submits a Quest, they will be required to add a link (e.g., GitHub). This way, the department manager (departments are: Software Development, Technical Support, Quality Assurance, Project Management, Information Security) where the "Employee" works can review and validate or reject the request. If the request is validated, the employee will receive the points for that Quest; otherwise, they can submit again with a new resolution.
<br>
<br>
✅ The CEO wants to have a leaderboard available in the application to observe the most active users, both to reward them additionally and to foster a competitive spirit within the community. 
<br>
<strong> --> </strong> This task was accomplished as follows: I did not create just a CEO; instead, I expanded the idea further. I thought that each department would have one or more managers (admins) who can view the ranking of employees within their respective department.
<br>
<br>
✅ The company has no idea about the types of badges and how they should be awarded, so they rely on your creativity and ideas.
<br>
<strong> --> </strong> I have created six types of badges: < 50 points: Potential, >= 50 points: Explorer, >= 75: points Innovator, >= 125 points: Challenger, >= 175 points: Visionary, >= 250 points: Mastermind.
<br>
<br>
✅ The company also has no idea how points/tokens could be used in a way that makes the effort to earn them worthwhile, so once again they rely on your creativity and ideas. 
<br>
<strong> --> </strong> I thought that the points could be used in a shop. In this shop, there are five types of products: Merchandise (50 points), Mousepad (75 points), Mouse (100 points), Keyboard (150 points), Day off (300 points). When an employee buys one of these products, their points balance will be updated, and based on the number of points, their badge will also be updated.
<br>

<h2>🎬 App Demo</h2>

You can watch a live demo [here](https://youtu.be/TTJU7LPrVjE).
