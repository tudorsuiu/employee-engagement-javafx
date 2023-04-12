<h2> © Credit </h2>
Totul in afara de iconitele utilizate in shop a fost conceput si creat de mine.
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

<h2> ℹ️ Despre </h2>
Compania Blue are nevoie de o soluție software prin care să crească engagement-ul angajaților, în așa fel încât aceștia să se implice în mai multe activități, în urma cărora să aibă un sentiment mai puternic de satisfacție. Studiile arată că un concept de gamification s-ar plia foarte bine pentru a rezolva problema într-un mod interactiv, oferind utilizatorilor o experiență faină.
<br>
Pentru crearea acestui proiect am folosit ca framework <strong>JavaFX</strong> si pentru stocarea datelor am utilizat <strong>PostgreSQL</strong>.

<h2> 🛠️ Functionalitati </h2>
✅ Utilizatorii pot câștiga tokens / puncte și badges în urma participării / rezolvării unor quest-uri.
<br>
<strong> --> </strong> Am implementat aceasta functionalitate astfel: angajatii pot castiga puncte in urma rezolvarii unor quest-uri, daca acestea sunt validate de catre managerul/managerii departamentului in care acestia sunt. Pe baza punctelor acumulate acestia vor primi cate un badge.
<br>
<br>
✅ Utilizatorii trebuie identificați iar datele aferente lor (punctaje, nume etc.) salvate. 
<br>
<strong> --> </strong> Pentru a salva datele utilizatorilor (Admin, Employee) dar si a altor clase utilizate (Quest) am folosit <strong>PostgreSQL</strong>.
<br>
<br>
✅ Quest-urile pot fi propuse de oricine, cu condiția să aibă suficienți tokens / puncte pentru a recompensa eventualii câștigători / participanți.
<br>
<strong> --> </strong> Questurile pot fi create doar de angajati pe baza punctelor pe care acestea le detin si doar in cadrul in departamentului in care acestia sunt. Daca un angajat incearca sa creeze un quest si nu are punctele necesare, o eroare cu un mesaj specific va fi afisata.
<br>
<br>
✅ Avem nevoie de o modalitate prin care cei ce și-au ales un quest să poată demonstra într-un mod simplu finalizarea acestuia. 
<br>
<strong> --> </strong> Pentru indeplinirea acestui task, in clasa Quest am adaugat un atribut "link" care initial va fi null si daca un "Employee" va da submit la Quest, acesta va fi obligat sa adauge un link (ex. github). Astfel managerul departamentului (departamentele sunt: Software Development, Technical Support, Quality Assurance, Project Management, Information Security) in care lucreaza acest "Employee" poate sa verifice si sa valideze/anuleze cererea acestuia. Daca este validata cererea, angajatul va primi punctele de pe acel "Quest", in caz contrar acesta va putea da din nou submit cu o noua rezolvare.
<br>
<br>
✅ CEO-ul dorește să aibă la dispoziție în aplicație un clasament din care poate observa cei mai activi utilizatori p̶e̶n̶t̶r̶u̶ ̶a̶-̶i̶ ̶p̶u̶t̶e̶a̶ ̶r̶e̶c̶o̶m̶p̶e̶n̶s̶a̶ ̶s̶u̶p̶l̶i̶m̶e̶n̶t̶a̶r̶, dar și pentru a trezi spiritul competitiv în comunitate. 
<br>
<strong> --> </strong> Acest task a fost indeplinit astfel: nu am creat doar un CEO, m-am gandit sa dezvolt putin ideea si sa o duc la un nivel mai amplu. M-am gandit ca fiecare departament va avea unul sau mai multi manageri (admini) care pot avea, pentru departamentul unde acesta este manager, rankingul cu angajatii din departamentul respectiv.
<br>
<br>
✅ Compania n-are idee despre care sunt tipurile de badges și modurile în care se acordă așa că apelează la creativitatea și ideile voastre. 
<br>
<strong> --> </strong> Am creat 6 tipuri de badge-uri: < 50 points: Potential, >= 50 points: Explorer, >= 75: points Innovator, >= 125 points: Challenger, >= 175 points: Visionary, >= 250 points: Mastermind.
<br>
<br>
✅ Compania n-are idee nici cum ar putea fi folosite punctele / tokens în așa fel încât să merite efortul pentru obținerea lor și din nou apelează la creativitatea și ideile voastre. 
<br>
<strong> --> </strong> M-am gandit ca punctele sa se foloseasca intr-un shop. In acest shop exista 5 tipuri de produse: Merchandise (50 puncte), Mousepad (75 puncte), Mouse (100 puncte), Keyboard (150 puncte), Day off (300 puncte). Cand angajatul cumpara unul dintre aceste produse, balanta sa de puncte adunate va fi updatata si, in functie de nr. de puncte, va fi updatat si badge-ul pe care acesta il detine.
<br>
