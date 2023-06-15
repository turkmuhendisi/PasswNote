package com.erdemserhat.ultimatebox.random_password_generator;

/**
 * The package aims to generate random password according to user's wish.
 * So, user have to be claim some information such as random password's
 * character length or which character the random password will contain
 * (for example numbers and characters which have 7 character.)
 */

public class Generator {

    /**
     * isCharacter, isNumerical, isSpecial and passwordLength are some data members for producing a password.
     */
    private boolean isCharacter;
    private boolean isNumerical;
    private int passwordLength;
    private boolean isSpecialCharacter;
    /**
     * Below, some default values are defined as final for selecting a character between them.
     */
    private final String numerical = "0123456789";
    private final String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String specialCharacter = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";


    /**
     * This is default constructor for instantiate an object from Generator class.
     * If the user wants the password contain numerical values then the parameter should be "true" otherwise should be "false".
     * If the user wants the password contain characters then the parameter should be "true" otherwise should be "false".
     * If the user wants the password contain special values such as ",+%&/(" then the parameter should be "true" otherwise "false".
     * Candidate password length should be claimed by the user with "passwordLength" parameter.
     * @param isNumerical
     * @param isCharacter
     * @param isSpecialCharacter
     * @param passwordLength
     */
    public Generator(boolean isNumerical, boolean isCharacter, boolean isSpecialCharacter, int passwordLength){
        this.isCharacter=isCharacter;
        this.isNumerical=isNumerical;
        this.passwordLength=passwordLength;
        this.isSpecialCharacter = isSpecialCharacter;
    }

    /**
     *If this parameter is called, the password will automatically contain any characters, numbers and special characters.
     * But the password length should be claimed as parameter.
     * Example Using;
     * Generator generator = new Generator(5);
     * generator.createPassword();
     * the code block will create passwords which contains characters, numbers, special characters with the length of 5 letters.
     * @param passwordLength
     */

    public Generator(int passwordLength){
        this.isCharacter=true;
        this.isNumerical=true;
        this.isSpecialCharacter=true;
        this.passwordLength=passwordLength;

    }

    /**
     * If this parameter is called by the programmer, a password which contain numbers, characters, special characters
     * with the length of 16 will be automatically created when generator.createPassword is called.
     */

    public Generator(){
        this.isCharacter=true;
        this.isNumerical=true;
        this.isSpecialCharacter=true;
        this.passwordLength=16;

    }

    /**
     * This is the method for creating a password according to the user's wish.
     * Warning !!
     * The method cannot be called without claiming any parameter in the constructor.
     * @return
     */
    public String createPassword() {
        String password = "";
        String text = "";

        if (this.isNumerical) {
            text += numerical;
        }
        if (this.isSpecialCharacter) {
            text += specialCharacter;
        }
        if (this.isCharacter) {
            text += character;
        }

        while (password.length() != passwordLength) {
            int randomIx = (int) (Math.random() * text.length());
            password += text.charAt(randomIx);
        }

        return password;
    }

    /**
     *
     * @param ex
     * @param ex2
     */
    public void exampleMethod(String ex,int ex2){


    }
    

}
