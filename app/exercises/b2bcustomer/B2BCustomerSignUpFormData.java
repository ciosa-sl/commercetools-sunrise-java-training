package exercises.b2bcustomer;

import com.commercetools.sunrise.myaccount.authentication.signup.DefaultSignUpFormData;

public class B2BCustomerSignUpFormData extends DefaultSignUpFormData {

    private boolean b2b;

    public boolean isB2b() {
        return b2b;
    }

    public void setB2b(final boolean b2b) {
        this.b2b = b2b;
    }
}
