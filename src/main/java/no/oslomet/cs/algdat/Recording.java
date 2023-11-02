package no.oslomet.cs.algdat;

import java.util.Objects;

public class Recording {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(7, 13);
        Rektangel rekt = new Rektangel(7, 13);

        System.out.println("Vanlig klasse: " + rect + "\nRecordklasse:  "+rekt);
        System.out.println("Areal vanlig klasse: " + rect.area() +"\nAreal recordklasse:  "+rect.area());

        System.out.println("De to klassene fungerer likt, men den ene var mye enklere å skrive.");
    }
}

class Rectangle {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Negativ bredde eller høyde (bredde="+width+", høyde="+height+").");
        this.width = width;
        this.height = height;
    }

    public double width() {return width;}
    public double height() {return height;}

    public double area() {return width*height;}

    @Override
    public String toString() {
        return getClass().getSimpleName()+"[width="+Double.toString(width)+
                ", height="+Double.toString(height)+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(width, rectangle.width) == 0 && Double.compare(height, rectangle.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}

record Rektangel(double width, double height) {
    public Rektangel {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Negativ bredde eller høyde (bredde="+width+", høyde="+height+").");
    }

    public double area() {return width*height;}
}
