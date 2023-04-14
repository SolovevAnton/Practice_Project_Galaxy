# PracticeProject-galaxy

Тренировочный проект по теме создание классов и работы с коллекциями

## Текст задачи

Тестовое задание по теме «Объектно-ориентированное 
программирование на языке Java»

Указания к выполнению задания:
•	Все классы должны удовлетворять Code Conventions for the Java Programming Language и принципам SOLID
•	В каждом классе должны быть описаны конструкторы по умолчанию, конструкторы с параметрами, инициализирующими поля классов, методы получения и установки значений в каждое из полей класса, метод toString, методы hashCode и equals

Задание:
1. Описать понятие планета, поля (название планеты, радиус в км, период обращения) и метод поведения планеты (название behavior), который возвращает строку, содержащую имя планеты и скорость вращения вокруг своей оси. Скорость вращения вычислять через метод класса.

2. Описать понятие галактика, поля (название галактики и список планет для текущей галактики) и метод поведения галактики (название behavior), который обращается к каждой планете из галактики, вызывает для нее behavior из п.1. Возвращает строку, содержащую все результаты вызовов behavior каждой планеты.
2.1. Реализовать метод добавления новой планеты в список всех планет текущей галактики. Если такая планета уже есть, то не добавлять ее в список
2.2. Реализовать методы поиска планеты по ее имени в галактике (возвращает объект планеты) и по объекту самой планеты (возвращает индекс вхождения)
2.3. Реализовать методы удаления планеты из галактики по ее имени (возвращает объект) и по объекту самой планеты (возвращает boolean)

3. Описать понятие вселенная, поля (минимум список галактик для текущей вселенной) определить самостоятельно
3.1. Во вселенной реализовать метод добавления новой галактики в список всех галактик
3.2. Реализовать методы:
•	поиска планеты из вселенной по имени (возвращает объект) и по объекту (возвращает массив из двух индексов: индекс галактики во вселенной и индекс планеты в найденной галактике),
•	метод поиска галактики из вселенной (по имени и по объекту).
3.3 Метод поведение, определить как генерацию случайным образом раз в 30 секунд случайного количества галактик со случайным числом планет, имена галактик и планет генерировать случайным образом (реализовать для этого отдельный класс со специализирующими методами): имя планеты должно начинаться с буквы Р, далее идет последовательность цифр , имя галактики аналогично , начиная с G. 

## Предположения
1. Радиус и период обращения планеты должы быть больше 0, в другом случае вылетает IlligalArgumentException
2. null Galaxy и null Planet возвращают null из метода поведение

## Вопросы
1. Как корректно описывать сценарий "сценарий по умолчанию"? Например планета - при создании пустым конструктором должна иметь все 0? Если каждая планета уникальна, должен ли дефолтный сценарий каждый раз создавать уникальную планету?
2. При создании дефолтной планеты с 0 периодом обращения - метод поведение будет выдавать ошибку. Корректно ли у для такой планеты прописывать 1 при объявлении поля?
3. Корректно ли прописаны комментарии к коду?
4. Корректно ли выбрано исключение IlligalArgumentException при указании радиуса, периода обращения планеты <= 0 в конструкторе?
5. Корректно ли использовать setter в конструкторе?
6. следует ли обрабатывать NullPointerException везде где они могут возникнуть? Например в методе behavior in Galaxy когда Planet == null;
7. Окей ли overload метод search in Galaxy, c учетом тогоб что без указания типа объекта вызов метода g.search(null) выдыет ошибку компиляции. Может лучше было сделать 2 разных метода?
8. Всегда ли следует возвращать protectedCopy если метод getter подразумевает возврат коллекции? Например в Galaxy class

