/**
 * Created by Ernesto Murillo on 7/19/2017.
 */
package neit.lab1fragments;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {
    String firstName;
    String lastName;
    String typeOfChocolate;
    int bars;
    Boolean shippingType;

    public Order(){

    }

    public Order(String firstName, String lastName, String typeOfChocolate, int bars, Boolean shippintType ){
        setFirstName(firstName);
        setLastName(lastName);
        setTypeOfChocolate(typeOfChocolate);
        setBars(bars);
        setShippintType(shippintType);
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypeOfChocolate() {
        return typeOfChocolate;
    }

    public void setTypeOfChocolate(String typeOfChocolate) {
        this.typeOfChocolate = typeOfChocolate;
    }

    public int getBars() {
        return bars;
    }

    public void setBars(int bars) {
        this.bars = bars;
    }

    public Boolean getShippintType() {
        return shippingType;
    }

    public void setShippintType(Boolean shippintType) {
        this.shippingType = shippintType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.typeOfChocolate);
        dest.writeInt(this.bars);
        dest.writeValue(this.shippingType);
    }

    protected Order(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.typeOfChocolate = in.readString();
        this.bars = in.readInt();
        this.shippingType = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
