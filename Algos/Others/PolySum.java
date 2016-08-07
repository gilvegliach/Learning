import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class PolySum {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new Monomial(0.f,-1.f), 
            new Monomial(2.f, 1.f));   // xˆ2 - 1
        Polynomial p2 = new Polynomial(new Monomial(3.f, 4.f),
            new Monomial(2.f, -2.f)); // 4xˆ3 - 2xˆ2
        System.out.println(p1.add(p2));    // 4x^3 - xˆ2 - 1 
    }
}

class Monomial {
    final float exp;
    final float coeff;
    Monomial(float e, float c) {
        exp = e;
        coeff = c;
    }

    public String toString() {
        if (exp == 0.f)
            return String.valueOf(coeff);
        return String.valueOf(coeff) + " x^" + String.valueOf(exp);
    }

    static class MonomialComparator implements Comparator<Monomial> {
        public int compare(Monomial t1, Monomial t2) {
            return (int)Math.round(t1.exp - t2.exp);
        }
    }

    final static Comparator<Monomial> COMPARATOR = new MonomialComparator();
}

class Polynomial {
    private final Monomial[] mMonomials;

    public Polynomial(Monomial... terms) {
        mMonomials = terms;
        Arrays.sort(mMonomials, Monomial.COMPARATOR);
    }

    Polynomial add(Polynomial p) {
        List<Monomial> result = new ArrayList();
        int n = mMonomials.length;
        int m = p.mMonomials.length;
        int i = 0, j = 0;
        while (i < n && j < m) {
            float exp1 = mMonomials[i].exp;
            float exp2 = p.mMonomials[j].exp;
            float coeff1 = mMonomials[i].coeff;
            float coeff2 = p.mMonomials[j].coeff;

            if (exp1 == exp2) {
                result.add(new Monomial(exp1, coeff1 + coeff2));
                i++;
                j++;                
            } if (exp1 < exp2) {
                result.add(mMonomials[i]);
                i++;
            } else { // exp2 < exp1
                result.add(p.mMonomials[j]);
                j++;
            }
        }
        
        if (i == n && j == m)
            return new Polynomial((Monomial[]) result.toArray(new Monomial[0]));
        
        while (i < n) 
            result.add(mMonomials[i++]);
        
        while (j < m)
            result.add(p.mMonomials[j++]);
       
        return new Polynomial((Monomial[]) result.toArray(new Monomial[0]));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int n = mMonomials.length;
        for (int i = 0; i < n; i++) {
             sb.append(mMonomials[i]);
             if (i < n - 1)
                 sb.append(" + ");
        }
        return sb.toString();
    }
}  
