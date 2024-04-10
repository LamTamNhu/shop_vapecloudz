package com.shop_vapecloudz.config;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
public class Credentials {
    static String clientId = "AV5TYrnVirk0spH4TFMO0fm4Pw26yck_MWzmpolMnrl8JCbuj4-hZrSt7fen-JxqTBuOcJmI3V_bAgvg";
    static String secret = "EOXD0MJU0a1P52qd3On3piTGkL5FqICR_q_yPiraq3cgceC3Uuj9oeQuO_oA5H0NBWIIXe3iDs-iq7NJ";

    // Creating a sandbox environment
    private static final PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, secret);

    // Creating a client for the environment
    public static PayPalHttpClient client = new PayPalHttpClient(environment);
}