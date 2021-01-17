package com.root.api.binance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.AssetBalance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserApiTest {
    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory
                .newInstance("myxNiMn1CeFMG2psTMDBgHUgQDXBBbJamxEwCDWrwdnC3Y6f87vtTaWTE9KcQj7O",
                            "ys4O5WcjGUpc8tJInhjLm9MNkjNReEeCbFWBgFbKShAPwWIR7DD8WEPN1MMWigKR");
        BinanceApiRestClient client = factory.newRestClient();

        // Get account balances
        Account account = client.getAccount(6_000L, System.currentTimeMillis());
        System.out.println(account.getAssetBalance("ETH"));

        List<AssetBalance> x = account.getBalances();




    }

}