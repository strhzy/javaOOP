import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main { 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        Добро пожаловать в меню управления замком!
        Выберите отдел для управления:
        ================================================================
        1. Жители
        2. Армия и война
        3. Казна
        4. Отношения
        5. Выйти
        """);
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                peopleMenu();
                break;
            case 2:
                armyMenu();
                break;
            case 3:
                treasuryMenu();
                break;
            case 4:
                relationsMenu();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }
    }
    public static void peopleMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        Выберите действие:
        ================================================================
        1. Добавить жителей
        2. Казнить жителей
        3. Выйти
        """);
        int choice = sc.nextInt();
        System.out.println("Введите количество: ");
        int num = sc.nextInt();
        switch(choice){
            case 1:
                new People().addPopulation(num);
                break;
            case 2:
                new People().execution(num);
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }
    }
    public static void armyMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        Выберите действие:
        ================================================================
        1. Объявить войну другому королевству
        2. Капитулировать
        3. Сформировать армию
        4. Расформировать армию
        5. Выйти
        """);
        int choice = sc.nextInt();
        int num;
        switch(choice){
            case 1:
                new Army().declareWar();
                break;
            case 2:
                new Army().capitulation();
                break;
            case 3:
                System.out.println("Введите количество: ");
                num = sc.nextInt();
                new Army().addArmy(num);
                break;
            case 4:
                System.out.println("Введите количество: ");
                num = sc.nextInt();
                new Army().removeArmy(num);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }
    }
    public static void treasuryMenu(){
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("""
        Выберите действие:
        ================================================================
        1. Добавить деньги
        2. Потратить деньги
        3. Выйти
        """);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Введите количество: ");
                num = sc.nextInt();
                new Treasury().addTreasury(num);
            case 2:
                System.out.println("Введите количество: ");
                num = sc.nextInt();
                new Treasury().removeTreasure(num);
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }
    }
    public static void relationsMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        Выберите действие:
        ================================================================
        1. Установить отношения
        2. Отменить отношения
        3. Выйти
        """);
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                new Relations().addTorgRelations();
                break;
            case 2:
                new Relations().removeTorgRelations();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }
    }
}

class MedievalCastle{
    protected static ArrayList<String> _relations = new ArrayList<>();
    protected static int _countPopulation = 0;
    protected static float _treasury = 0;
    protected static int _countArmy = 0;
}

class People extends MedievalCastle {
    public static void addPopulation(int population) {
        if (population < 0) {
            System.out.println("Число не должно быть отрицательным");
            return;
        }
        _countPopulation += population;
        
        System.out.println("В замке " + _countPopulation + " жителей");
    }

    public void execution(int count){
        if (count < 0){
            System.out.println("Число не должно быть отрицательным");
            return;
        }
        else if(count > _countPopulation) {
            System.out.println("Нельзя казьнить людей больше чем есть");
            return;
        }
        
        _countPopulation -= count;
        System.out.println("Казьнено " + count + " людей\nОсталось " + _countPopulation + " людей");
    }
}

class Army extends MedievalCastle {
    public void declareWar(){
        Random rand = new Random();
        boolean victory = rand.nextBoolean();
        if(victory){
            int death = rand.nextInt(_countArmy);
            _countArmy -= death;
            System.out.println("Ваше королевство победило\nПотери составляют " + death + " человек");
        }
        else{
            _countArmy = 0;
            System.out.println("Ваше королевство проиграло\nВся армия уничтожена");
        }
    }

    public void addArmy(int count) {
        if(count > _countPopulation) {
            System.out.println("У вас недостаточно жителей");
            return;
        }
        else if(count > 0) {
            _countArmy += count;
            _countPopulation -= _countPopulation;
        }
        else return;
        
        System.out.println("Численность армии " + _countArmy + " человек");
    }
    
    public void removeArmy(int count) {
        if (count < 0) {
            System.out.println("Число должно быть больше нуля");
            return;
        } else if (count > _countArmy) {
            System.out.println("Ваша армия меньше");
            return;
        }

        _countArmy -= count;
        _countPopulation += count;
        System.out.println("Численность армии " + _countArmy + " человек");
    }

    public void capitulation(){
        _countPopulation = 0;
        _countArmy = 0;
        _treasury = 0;
        System.out.println("Вы капитулировали :(");
    }
}

class Treasury extends MedievalCastle {
    public void addTreasury(float money) {
        if (money < 0){
            System.out.println("Число не должно быть отрицательным");
            return;
        }
        
        _treasury += money;
        System.out.println("В казне " + _treasury + " золота");
    }

    public void removeTreasure(float money){
        if (money < 0){
            System.out.println("Число не должно быть отрицательным");
            return;
        }
        else if(money > _treasury) {
            System.out.println("В казне нет столько золота");
            return;
        }

        _treasury -= money;
        System.out.println("В казне осталось " + _treasury + " золота");
    }
}

class Relations extends MedievalCastle {
    public static void addTorgRelations(){
        System.out.println("С каким королевством будем торговать?");
        Scanner sc = new Scanner(System.in);
        _relations.add(sc.nextLine());
        sc.close();
    }
    
    public static void removeTorgRelations(){
        if(_relations.isEmpty()) {
            System.out.println("Вы и так ни с кем не торгуете");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("С каким королевством хотите разорвать торговые отношения");
        for (int i = 1; i <= _relations.size(); i++) {
            System.out.println(i + ". " + _relations.get(i-1));
        }
        int index = sc.nextInt();
        
        _relations.remove(index-1);
        sc.close();
    }
}