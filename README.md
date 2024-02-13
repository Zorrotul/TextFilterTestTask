Утилита фильтрации содержимого файлов.
Данная утилита решает следующую задачу:
При запуске утилиты в командной строке подается несколько файлов, содержащих в
перемешку целые числа, строки и вещественные числа. В качестве разделителя
используется перевод строки. Строки из файлов читаются по очереди в соответствии с их
перечислением в командной строке.

Задача утилиты записать разные типы данных в разные файлы. Целые числа в один
выходной файл, вещественные в другой, строки в третий. По умолчанию файлы с
результатами располагаются в текущей папке с именами integers.txt, floats.txt, strings.txt.
Дополнительно с помощью опции -o нужно уметь задавать путь для результатов. Опция -p
задает префикс имен выходных файлов. Например -o /some/path -p result_ задают вывод в
файлы /some/path/result_integers.txt, /some/path/result_strings.txt и тд.
По умолчанию файлы результатов перезаписываются. С помощью опции -a можно задать
режим добавления в существующие файлы.

Файлы с результатами должны создаваться по мере необходимости. Если какого-то типа
данных во входящих файлах нет, то и создавать исходящий файл, который будет заведомо
пустым, не нужно.

В процессе фильтрации данных необходимо собирать статистику по каждому типу данных.
Статистика должна поддерживаться двух видов: краткая и полная. Выбор статистики
производится опциями -s и -f соответственно. Краткая статистика содержит только
количество элементов записанных в исходящие файлы. Полная статистика для чисел
дополнительно содержит минимальное и максимальное значения, сумма и среднее.
Полная статистика для строк, помимо их количества, содержит также размер самой
короткой строки и самой длинной.
Статистику по каждому типу отфильтрованных данных утилита должна вывести в консоль.

Допущения реализации:  
1) Имена входных файлов должны быть в конце передаваемых параметров.  
2) Если в строке файла не одно вещественное или целое число, то строка считается "строкой"

Особенности реализации:

• Версия Java: jdk-17.0.8;  
• Система сборки: gradle-7.6.1;  
• Кодировка проекта windows-1251.  

Примеры команд для запуска через командную строку:

(путь до jdk)...\jdk-17.0.8\bin\java -jar sortIt-1.0.jar -f -o some/path -a -p sample- in1.txt in2.txt in3.txt

(путь до jdk)...\jdk-17.0.8\bin\java -jar sortIt-1.0.jar -s -o some/path -a -p dont|do*it- i/n1.txt in2.txt in3.txt

(путь до jdk)...\jdk-17.0.8\bin\java -jar sortIt-1.0.jar in1.txt in2.txt
