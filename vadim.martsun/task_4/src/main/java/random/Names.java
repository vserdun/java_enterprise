package random;

import objects.Gender;

public enum Names {
    JAMES("James", Gender.MALE),
    LILY("Lily", Gender.FEMALE),
    MICHAIL("Michail", Gender.MALE),
    MARK("Mark", Gender.MALE),
    VADIM("Vadim", Gender.MALE),
    RICHARD("Richard", Gender.MALE),
    DAVID("David", Gender.MALE),
    ANASTASIA("Anastasia", Gender.FEMALE),
    JULIA("Julia", Gender.FEMALE),
    PATRIK("Patrick", Gender.MALE),
    ANDREW("Andrew", Gender.MALE),
    ANGELINA("Angelina", Gender.FEMALE);

    private String name;

    private Gender gender;

    Names(String name, Gender gender){
        this.name = name;
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public Gender getGender(){
        return gender;
    }
}
