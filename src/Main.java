import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Тут ничего нет
    }
}

class MedievalCastle{
    private static ArrayList<String> _relations = new ArrayList<>();
    
    private static int _countPopulation = 0;
    private static float _treasury = 0;
    private static int _countArmy = 0;
    
    
    public static void addPopulation(int population) {
        if (population < 0) {
            System.out.println("Число не должно быть отрицательным");
            return;
        }
        _countPopulation += population;
        
        System.out.println("В замке " + _countPopulation + " жителей");
    }

    
    public static void addTorgRelations(){
        System.out.println("С каким королевством будем торговать?");
        Scanner sc = new Scanner(System.in);
        _relations.add(sc.nextLine());
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
    
    public void capitulation(){
        _countPopulation = 0;
        _countArmy = 0;
        _treasury = 0;
        System.out.println("Вы капитулировали :(");
    }
    
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
    
}