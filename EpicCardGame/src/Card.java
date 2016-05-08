import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * The card class. It is responsible for every card that the
 * player can put on the board.
 * The card class contains:
 * 1. One or more spells to use against enemies.
 * 2. Health points, which if reduced to or below 0 results in the card
 * being destroyed.
 * 3. A picture of the card.
 * 4. A name
 *
 * This class is supposed to create a card and then generate it to a visible thing
 * which will be drawn on the canvas.
 */
public class Card {

    private String nameString;
    private ArrayList<Spell> spells;
    private final int HEALTH;
    private int currentHp;
    private Image image; //loads the image
    private ImageView iv; //creates the visble object
    private ArrayList<Label> spellInfo;
    private Label name;
    private int posX, posY;

    private final Type type;

    /*
        when each card is created it's flip = true, which means that
        the card is flipped. card being flipped means that you only see the
        back of it which is identical to each card. When the card is put into your hand
        flip is set to false meaning that you will be able to see the cards.

        use this variable in your game loop to check if the card is flipped or not.
    */
    private boolean flip;

    public Card(Image png, String name, int hp, ArrayList<Spell> spells, Type t) {

        type = t;

        posX = 0;
        posY = 0;

        flip = true;

        image = png;
        iv = new ImageView();
        nameString = name;
        this.name = new Label(name);
        this.spells = spells;
        HEALTH = hp;
        currentHp = hp;
        iv.setImage(image);
        //getSpellInfo(); //puts the spell info into the ArrayList smoothly so it can be taken in generateCard method.
    }

    public Image getImage() {
        return image;
    }

    public Type getType() {
        return type;
    }

    //förstör kortet
    private void destroy() {

    }

    public void setPosX(int x) {
        posX = x;
    }

    public void setPosY(int y) {
        posY = y;
    }

    public void setPos(int x, int y) {
        posX = x;
        posY = y;
    }

    public void draw() {

    }

    public ArrayList<Spell> getSpells() {
        ArrayList<Spell> list = new ArrayList<>();
        
        for(Spell s : spells) {
            list.add(new Spell(s.getName(), s.getPower(), s.getDiceNumber()));
        }
        return list;
    }

    public boolean getFlip() {
        return flip;
    }

    public void flipCard() {
        flip = false;
    }

    /**
     * Another help method used to put the spellInfo in
     * the form of Strings, into the VBox.
     * @param card The card you wish to add the info to.
     */
    private void generateSpell(VBox card) {

        spellInfo = new ArrayList<Label>();
        /*
        Nu hamnar all spellinfo i Vboxen.
         */
        for (Spell s : spells) {
            //spellInfo.add(s.getInfo());
            card.getChildren().add(s.getInfo());

        }

    }


    public String getName() {
        return nameString;
    }

    public int getHealth() {
        return HEALTH;
    }

    /*
	How to use this function.. Create a Card object and define its fields etc.
	This method will generate the cards, set each things to pieces like name on top,
	image under, and spells under that etc.
	To then draw this on the canvas you will write in code: root.getChildren.add(card1.generateCard());
	 */
    public VBox generateCard(Group root) {

        VBox card = new VBox();

        VBox cardName = new VBox();
        VBox cardImage = new VBox();
        VBox cardSpells = new VBox();

        cardName.getChildren().addAll(name);
        cardName.getStyleClass().addAll("vboxName");

        card.getStyleClass().addAll("vbox");

        iv.prefHeight(100);
        iv.prefWidth(100);

        iv.setFitHeight(100 * 1.6);
        iv.setFitWidth(100 * 1.6);

        cardImage.getChildren().addAll(iv);
        cardImage.getStyleClass().addAll("vboxImage");

        generateSpell(cardSpells); //adds the spells to this vbox
        cardSpells.getStyleClass().addAll("vboxSpell");


        card.setTranslateX(posX);
        card.setTranslateY(posY);

        /*
        The card background color is set based on its Type
         */

        card.getChildren().addAll(cardName, cardImage, cardSpells); //add the card name first then its image

        //sets card Color based on type
        if(type.equals(Type.BAD)) {

        }

        if(type.equals(Type.HAPPY)) {

        }

        if(type.equals(Type.BRAVE)) {

        }

        if(type.equals(Type.CUTE)) {
            card.setStyle("-fx-background-color: linear-gradient(#d52242, #d59ca8)");
        }

        if(type == Type.TALENTED) {
            card.setStyle("-fx-background-color: linear-gradient(#d52242, #d59ca8, #76d585)");
        }

        /*
        Fix this so that the cards are put into the deck and not in the root directly
         */
        root.getChildren().addAll(card);

        return card;
    }

}
