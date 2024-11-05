#include <iostream>
using namespace std;

class Commodity {
protected:
    string name;
    long long phone;

public:
    Commodity() {
        cout << "Welcome to Indian-Oil-Corporation Limited." << endl << endl;
        cout << "Enter your details correctly." << endl;
        cout << "Details will be recorded." << endl << endl;
    }

    virtual void display() {
        cout << "If there is any doubt, contact us." << endl;
        cout << "Contact No: 8078406286." << endl << endl;
    }

    virtual ~Commodity() = default;
};

class PersonalDetails : virtual public Commodity {
protected:
    int id;

public:
    PersonalDetails() {
        cout << "Please enter your Personal Details." << endl << endl;
    }

    void inputDetails() {
        cout << "Enter your name: ";
        cin >> name;

        do {
            cout << "Enter your Phone Number (10-digits): ";
            cin >> phone;

            if (phone >= 1000000000 && phone <= 9999999999) {
                break;
            } else {
                cout << "Invalid Phone Number. Please enter your Phone Number correctly." << endl;
            }
        } while (true);

        do {
            cout << "Enter your Customer-id (5-digits): ";
            cin >> id;

            if (id >= 10000 && id <= 99999) {
                break;
            } else {
                cout << "Invalid ID." << endl << endl;
            }
        } while (true);
    }

    void display() override {
        cout << "Name of the customer: " << name << endl;
        cout << "Mobile no: " << phone << endl;
        cout << "Customer ID: " << id << endl;
    }
};

class Constructor : virtual public Commodity, public PersonalDetails {
protected:
    string type; 
    int volume;  
    string custype; 

public:
    Constructor(string t = "", int v = 0) : type(t), volume(v) {}

    Constructor(const Constructor &c) : Commodity(c), PersonalDetails(c), type(c.type), volume(c.volume) {}

    void display() override {
        Commodity::display();
        PersonalDetails::display();
        cout << "Customer type: " << type << endl;
        cout << "Max Barrels can Purchase: " << volume << endl;
    }

    void inputCustomerType() {
        do {
            cout << "Enter the customer Type (Regular/VIP): ";
            cin >> custype;

            if (custype == "regular") {
                volume = 20;
                type = "Regular";
                cout << "You will get 20 barrels." << endl;
                break;
            } else if (custype == "vip") {
                volume = 50;
                type = "VIP";
                cout << "You will get 50 barrels." << endl;
                break;
            } else {
                cout << "Wrong customer type." << endl << endl;
            }
        } while (true);
    }

    string customertype() {
        return custype;
    }

    Constructor operator++() {
        cout << "As a Diwali sale, you get 1 extra barrel as a gift." << endl;
        ++volume;
        return *this;
    }
};

class Price : public Constructor {

public:
	float barprice;
	float tprice;
	
    Price(float p = 0) : Constructor(), barprice(p), tprice(0) {}

    void calculateTotalPrice() {
        tprice = barprice * volume;
    }

    void display() {
        cout << "Total price of the barrels: " << tprice << endl;
    }

    friend Price operator*(Price &pr);
    friend Price operator-(Price &pi);
};

Price operator*(Price &pr) {
    Price temp;
    temp.tprice = pr.barprice * pr.volume;
    return temp;
}

Price operator-(Price &pi) {
    Price temp;
    temp.tprice = pi.tprice - 2 * pi.barprice; 
    return temp;
}

class PutDetails : public Price {
public:
    void display() override {
        Constructor::display();
        cout << "Customer Type: " << custype << endl;
        cout << "Number of barrels: " << volume << endl;
        cout << "Total Price: " << tprice << endl;
    }
};

class Repeat : public PutDetails {
private:
    int vipcount = 0;
    int regularcount = 0;
    static int stacount; 
    int ratings[5] = {0}; 

public:
    void count() {
        if (customertype() == "vip") {
            vipcount++;
        } else if (customertype() == "regular") {
            regularcount++;
        }
    }

    bool continueProgram() {
        char decision;
        cout << "Do you want to continue the program? (Y/N): ";
        cin >> decision;
        return decision == 'Y' || decision == 'y';
    }

    void showCustomerCount() {
        cout << "Total number of VIP customers: " << vipcount << endl;
        cout << "Total number of Regular customers: " << regularcount << endl;
    }

    void rateService() {
        int rate;
        do {
            cout << "Please rate our service from 1 to 5 stars: ";
            cin >> rate;

            if (rate >= 1 && rate <= 5) {
                ratings[rate - 1]++;
                cout << "Thank you for your rating!" << endl;
                break;
            } else {
                cout << "Invalid rating. Please enter a rating between 1 and 5." << endl;
            }
        } while (true);
    }

    void showRatings() const {
        cout << "Service Ratings:" << endl;
        for (int i = 0; i < 5; i++) {
            cout << (i + 1) << " star: " << ratings[i] << endl;
        }
        stacount++;
    }

    static void countDetails()
    {
    	cout << "Number of customer details entered: " << stacount << endl;
	}
};

int Repeat::stacount = 0;

int main() {
    Repeat customer;

    do {
        customer.inputDetails();
        customer.inputCustomerType();
        customer.barprice = 10000;
        customer.calculateTotalPrice();
        customer.display();
        customer.count();
        customer.rateService();
        customer.showRatings();
    } while (customer.continueProgram());

    Repeat::countDetails();
    return 0; 
}

