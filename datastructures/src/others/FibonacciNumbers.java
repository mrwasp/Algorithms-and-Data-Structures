package others;

import java.math.BigInteger;

public class FibonacciNumbers {
  private static class Matrix {
    BigInteger a;
    BigInteger b;
    BigInteger c;
    BigInteger d;

    Matrix(BigInteger a, BigInteger b, BigInteger c, BigInteger d) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
    }

    @Override
    public String toString() {
      return a + " | " + b + "\n" + c + " | " + d + "\n";
    }
  }

  private static Matrix mul(Matrix m1, Matrix m2) {
    return new Matrix(
        (m1.a.multiply(m2.a)).add(m1.b.multiply(m2.c)),
        (m1.a.multiply(m2.b)).add(m1.b.multiply(m2.d)),
        (m1.c.multiply(m2.a)).add(m1.d.multiply(m2.c)),
        (m1.c.multiply(m2.b)).add(m1.d.multiply(m2.d))
    );
  }

  private static Matrix createBasicMatrix() {
    return new Matrix(
        new BigInteger("1"), new BigInteger("1"),
        new BigInteger("1"), new BigInteger("0"));
  }

  private static Matrix createIdentityMatrix() {
    return new Matrix(
        new BigInteger("1"), new BigInteger("1"),
        new BigInteger("1"), new BigInteger("1"));
  }

  public static BigInteger standardAlgorithm(int n) {
    long begin = System.currentTimeMillis();
    if (n < 2) return new BigInteger(Integer.toString(n));
    BigInteger f1 = new BigInteger("0");
    BigInteger f2 = new BigInteger("1");
    for (int i = 1; i < n; i++) {
      BigInteger temp = f2.add(f1);
      f1 = f2;
      f2 = temp;
    }
    System.out.println("Standard algorithm time: " + (System.currentTimeMillis() - begin));
    return f2;
  }

  public static BigInteger fastAlgorithm(int n) {
    long begin = System.currentTimeMillis();
    if (n < 2) return new BigInteger(Integer.toString(n));
    Matrix powerOfTwo = createBasicMatrix();
    Matrix result = createIdentityMatrix();
    n -= 1;
    while (n > 0) {
      if (n % 2 == 1) result = mul(result, powerOfTwo);
      powerOfTwo = mul(powerOfTwo, powerOfTwo);
      n /= 2;
    }
    System.out.println("Fast algorithm time: " + (System.currentTimeMillis() - begin));
    return result.b;
  }

  public static void main(String[] args) {
    System.out.println("Test - 1_000_000 fibonacci number finding");
    standardAlgorithm(1_000_000); // 21.1s
    fastAlgorithm(1_000_000); //0.67s
  }

}
