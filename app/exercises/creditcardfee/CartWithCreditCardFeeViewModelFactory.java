package exercises.creditcardfee;

import com.commercetools.sunrise.framework.viewmodels.content.addresses.AddressViewModelFactory;
import com.commercetools.sunrise.framework.viewmodels.content.carts.*;
import com.commercetools.sunrise.framework.viewmodels.formatters.PriceFormatter;
import io.sphere.sdk.carts.Cart;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.money.CurrencyUnit;

import static exercises.creditcardfee.CreditCardFeeUtils.findAppliedCreditCardFee;

public class CartWithCreditCardFeeViewModelFactory extends CartViewModelFactory {

    @Inject
    public CartWithCreditCardFeeViewModelFactory(final CurrencyUnit currency, final PriceFormatter priceFormatter,
                                                 final ShippingInfoViewModelFactory shippingInfoViewModelFactory,
                                                 final PaymentInfoViewModelFactory paymentInfoViewModelFactory,
                                                 final AddressViewModelFactory addressViewModelFactory,
                                                 final LineItemExtendedViewModelFactory lineItemExtendedViewModelFactory) {
        super(currency, priceFormatter, shippingInfoViewModelFactory, paymentInfoViewModelFactory, addressViewModelFactory, lineItemExtendedViewModelFactory);
    }

    @Override
    protected CartViewModel newViewModelInstance(@Nullable final Cart cart) {
        final CartViewModel viewModel = super.newViewModelInstance(cart);
        fillCreditCardFee(viewModel, cart);
        return viewModel;
    }

    private void fillCreditCardFee(final CartViewModel viewModel, @Nullable final Cart cart) {
        if (cart != null) {
            findAppliedCreditCardFee(cart)
                    .ifPresent(creditCardFee -> viewModel.put("creditCardFee", getPriceFormatter().format(creditCardFee.getTotalPrice())));
        }
    }
}
