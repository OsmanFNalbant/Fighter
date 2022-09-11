import java.util.Random;
class Fighter {
    String name;
    int damage;
    int health;
    int weight;
    double dodge;

    public Fighter(String name, int damage, int health, int weight, double dodge) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.weight = weight;
        this.dodge = dodge;
    }

    public int hit(Fighter foe) {
        System.out.println("------------");
        System.out.println(this.name + " => " + foe.name + " " +  this.damage + " hasar vurdu.");

        if (foe.dodge()) {
            System.out.println(foe.name + " gelen hasarı savurdu.");
            return foe.health;
        }

        if (foe.health - this.damage < 0)
            return 0;

        return foe.health - this.damage;
    }

    public boolean dodge() {
        double randomValue = Math.random() * 100;  //0.0 to 99.9
        return randomValue <= this.dodge;
    }
}



class Ring {
    Fighter f1;
    Fighter f2;
    int minWeight;
    int maxWeight;

    public Ring(Fighter f1, Fighter f2, int minWeight, int maxWeight) {
        this.f1 = f1;
        this.f2 = f2;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public void run() {

        if (checkWeight()) {
            while (f1.health > 0 && f2.health > 0) {
                System.out.println("======== YENİ ROUND ===========");
                f2.health = f1.hit(f2);
                if (isWin()){
                    break;
                }
                f1.health = f2.hit(f1);
                if (isWin()){
                    break;
                }
                printScore();
            }

        } else {
            System.out.println("Sporcuların ağırlıkları uyuşmuyor.");
        }


    }

    public boolean checkWeight() {
        return (f1.weight >= minWeight && f1.weight <= maxWeight) && (f2.weight >= minWeight && f2.weight <= maxWeight);
    }

    public boolean isWin() {
        if (f1.health == 0) {
            System.out.println("Maçı Kazanan : " + f2.name);
            return true;
        } else if (f2.health == 0){
            System.out.println("Maçı Kazanan : " + f2.name);
            return true;
        }

        return false;
    }


    public void printScore() {
        System.out.println("------------");
        System.out.println(f1.name + " Kalan Can \t:" + f1.health);
        System.out.println(f2.name + " Kalan Can \t:" + f2.health);
    }
}


public class Main {
    public static void main(String[] args) {
        Fighter marc = new Fighter("Marc" , 15 , 100, 90, 0);
        Fighter alex = new Fighter("Alex" , 10 , 95, 100, 25);
        Fighter john = new Fighter("John",12,85,95,5);
        int c =0;
        while(c!=4)
        {
        int a = choose();
        int b = choose();
        if(a==b){
             b = choose();
        }
        if(a!=b){
            if(a==0 && b==1)
            {
                Ring r = new Ring(marc,alex , 90 , 100);
                r.run();
            }
            if(a==0 && b==2)
            {
                Ring r = new Ring(marc,john , 90 , 100);
                r.run();
            }
            if(a==1 && b==0)
            {
                Ring r = new Ring(alex,marc, 90 , 100);
                r.run();
            }
            if(a==1 && b==2)
            {
                Ring r = new Ring(alex,john , 90 , 100);
                r.run();
            }
            if(a==2 && b==0)
            {
                Ring r = new Ring(john,marc , 90 , 100);
                r.run();
            }
            if(a==2 && b==1)
            {
                Ring r = new Ring(john,alex , 90 , 100);
                r.run();
            }
        c=4;
        }
        }
    }
    public static int choose()
    {
        Random r=new Random();
        int a=r.nextInt(3);
        return a;
    }
}
