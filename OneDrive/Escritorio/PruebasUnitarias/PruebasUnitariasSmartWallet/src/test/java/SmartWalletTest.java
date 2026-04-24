import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SmartWalletTest {

    private SmartWallet billetera;

    @BeforeEach
    public void setUp() {
        billetera = new SmartWallet("Standard");
    }

    @Test
    public void testDepositoNormal() {
        boolean exito = billetera.deposit(50.0);
        assertTrue(exito);
        assertEquals(50.0, billetera.getBalance());
    }

    @Test
    public void testDepositoCashback() {
        boolean exito = billetera.deposit(200.0);
        assertTrue(exito);
        assertEquals(202.0, billetera.getBalance());
    }

    @Test
    public void testDepositoCienExacto() {
        boolean exito = billetera.deposit(100.0);
        assertTrue(exito);
        assertEquals(100.0, billetera.getBalance());
    }

    @Test
    public void testSuperaLimite() {
        boolean exito = billetera.deposit(5000.0);
        assertFalse(exito);
        assertEquals(0.0, billetera.getBalance());
    }

    @Test
    public void testLlegaLimiteExacto() {
        for (int i = 0; i < 50; i++) {
            billetera.deposit(100.0);
        }
        assertEquals(5000.0, billetera.getBalance());
    }

    @Test
    public void testDepositoInvalido() {
        assertFalse(billetera.deposit(0.0));
        assertFalse(billetera.deposit(-50.0));
    }

    @Test
    public void testRetiroNormal() {
        billetera.deposit(100.0);
        boolean exito = billetera.withdraw(40.0);
        assertTrue(exito);
        assertEquals(60.0, billetera.getBalance());
        assertTrue(billetera.isActive());
    }

    @Test
    public void testRetiroSinFondos() {
        billetera.deposit(100.0);
        boolean exito = billetera.withdraw(150.0);
        assertFalse(exito);
        assertEquals(100.0, billetera.getBalance());
    }


    @Test
    public void testRetiroInvalido() {
        billetera.deposit(100.0);
        assertFalse(billetera.withdraw(0.0));
        assertFalse(billetera.withdraw(-20.0));
    }
}