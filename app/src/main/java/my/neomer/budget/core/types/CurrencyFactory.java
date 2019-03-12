package my.neomer.budget.core.types;

import android.text.Html;

public class CurrencyFactory {

    private CurrencyFactory() {

    }

    public static Currency getCurrencyByShortName(String name) {
        switch (name.toLowerCase()) {
            case "р":
            case "руб":
            case "rub":
                return new Currency("Rouble", "RUB", Html.fromHtml(" &#x20bd").toString());

            case "usd":
                return new Currency("United States Dollar", "USD", Html.fromHtml(" &#x0024").toString());

            case "gbp":
                return new Currency("Pound Sterling", "GBP", Html.fromHtml(" &#x00a3").toString());

            default:
                return null;
        }
    }

    public static Currency getCurrencyByFullName(String name) {
        switch (name.toLowerCase()) {
            case "rouble":
                return new Currency("Rouble", "RUB", Html.fromHtml(" &#x20bd").toString());

            case "united states dollar":
                return new Currency("United States Dollar", "USD", Html.fromHtml(" &#x0024").toString());

            case "pound sterling":
                return new Currency("Pound Sterling", "GBP", Html.fromHtml(" &#x00a3").toString());

            default:
                return null;
        }

    }

    public  static Currency getCurrencyByCountry(String country) {
        switch (country.toLowerCase()) {
            case "rus":
            case "russia":
                return new Currency("Rouble", "RUB", Html.fromHtml(" &#x20bd").toString());

            case "usa":
            case "united states of america":
            case "east timor":
            case "ecuador":
            case "el salvador":
            case "republic of el salvador":
            case "federated states of micronesia":
            case "marshall islands":
            case "palau":
            case "panama":
            case "zimbabwe":
                return new Currency("United States Dollar", "USD", Html.fromHtml(" &#x0024").toString());

            case "Guernsey":
            case "gb":
            case "great britain":
                return new Currency("Pound Sterling", "GBP", Html.fromHtml(" &#x00a3").toString());

            default:
                return null;
        }
    }

}
