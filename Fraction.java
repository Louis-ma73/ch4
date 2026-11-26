import java.util.Random;

/**
 * A Fraction represents a ratio of two integers.
 * Part 2 additions: the default constructor makes a random fraction,
 * and equals(Fraction) compares numerators and denominators exactly.
 */
public class Fraction
{
    private static final Random RAND = new Random();

    private int numerator;
    private int denominator;

    /**
     * Makes a random fraction whose numerator and denominator are each
     * a random integer from 1 to 9.
     */
    public Fraction()
    {
        numerator = RAND.nextInt(9) + 1;
        denominator = RAND.nextInt(9) + 1;
    }

    /**
     * Makes the fraction n/d exactly as given (it is NOT reduced).
     * A negative sign is moved to the numerator.
     */
    public Fraction(int n, int d)
    {
        if (d == 0)
        {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        if (d < 0)
        {
            n = -n;
            d = -d;
        }
        numerator = n;
        denominator = d;
    }

    /**
     * Makes a fraction from text such as "11/15", "-1/28" or "3" (which means 3/1).
     * Throws NumberFormatException if the text is not a fraction.
     */
    public Fraction(String s)
    {
        this(parseNumerator(s), parseDenominator(s));
    }

    private static int parseNumerator(String s)
    {
        s = s.trim();
        int slash = s.indexOf('/');
        if (slash == -1)
        {
            return Integer.parseInt(s);
        }
        return Integer.parseInt(s.substring(0, slash).trim());
    }

    private static int parseDenominator(String s)
    {
        s = s.trim();
        int slash = s.indexOf('/');
        if (slash == -1)
        {
            return 1;
        }
        int d = Integer.parseInt(s.substring(slash + 1).trim());
        if (d == 0)
        {
            throw new NumberFormatException("Denominator cannot be zero");
        }
        return d;
    }

    // ---------- getters and setters ----------

    public int getNumerator()
    {
        return numerator;
    }

    public int getDenominator()
    {
        return denominator;
    }

    public void setNumerator(int n)
    {
        numerator = n;
    }

    public void setDenominator(int d)
    {
        if (d == 0)
        {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        denominator = d;
    }

    // ---------- arithmetic (results are in lowest terms) ----------

    public Fraction add(Fraction other)
    {
        int n = numerator * other.denominator + other.numerator * denominator;
        int d = denominator * other.denominator;
        return new Fraction(n, d).reduce();
    }

    public Fraction subtract(Fraction other)
    {
        int n = numerator * other.denominator - other.numerator * denominator;
        int d = denominator * other.denominator;
        return new Fraction(n, d).reduce();
    }

    public Fraction multiply(Fraction other)
    {
        int n = numerator * other.numerator;
        int d = denominator * other.denominator;
        return new Fraction(n, d).reduce();
    }

    public Fraction divide(Fraction other)
    {
        if (other.numerator == 0)
        {
            throw new ArithmeticException("Cannot divide by zero");
        }
        int n = numerator * other.denominator;
        int d = denominator * other.numerator;
        return new Fraction(n, d).reduce();
    }

    /**
     * Returns a new Fraction equal in value to this one, in lowest terms.
     * Zero becomes 0/1.
     */
    public Fraction reduce()
    {
        if (numerator == 0)
        {
            return new Fraction(0, 1);
        }
        int g = gcd(Math.abs(numerator), Math.abs(denominator));
        return new Fraction(numerator / g, denominator / g);
    }

    private static int gcd(int a, int b)
    {
        while (b != 0)
        {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // ---------- other methods ----------

    public double toDouble()
    {
        return (double) numerator / denominator;
    }

    /**
     * Returns true if Fraction f is identical to this fraction. It does not take into
     * account if two fractions are equal in value, just if the numerators and
     * denominators are the same.
     *
     * @param f    The fraction to compare with
     * @return     A boolean value that represents whether f was equal to this Fraction
     */
    public boolean equals(Fraction f)
    {
        return f != null
            && numerator == f.numerator
            && denominator == f.denominator;
    }

    public String toString()
    {
        return numerator + "/" + denominator;
    }