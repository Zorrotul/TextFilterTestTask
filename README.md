Данное приложение производит сортировку слиянием нескольких файлов.
Параметры программы задаются при запуске через аргументы командной строки, по порядку: 
1. режим сортировки -a или -d (asc или desc), необязательный, по умолчанию сортирует по возрастанию; 
2. тип данных -s или -i, (string или ineger) обязательный; 
3. имя выходного файла, обязательное; 
4. остальные параметры – имена входных файлов, не менее одного.

Пример входных параметров:  

-a -i outputfile.txt inputfile1.txt inputfile2.txt  

-d -i outputfile.txt inputfile3.txt inputfile4.txt  

-a -s outputStringfile.txt inputStringfile1.txt inputStringfile2.txt  

-d -s outputStringfile.txt inputStringfile3.txt inputStringfile4.txt  


Версия java:
Для запуска рекоммендуется jdk-17.0.8

Первая версия приложения лежит в build/libs/sortIt-0.0.1-SNAPSHOT.jar для запуска через командную строку можно использовать команду(вместо троеточия нужно вставить путь к jdk): 

...\tools\jdk-17.0.8\bin\java -jar sortIt-0.0.1-SNAPSHOT.jar -s outputStringfile.txt inputStringfile1.txt inputStringfile2.txt
