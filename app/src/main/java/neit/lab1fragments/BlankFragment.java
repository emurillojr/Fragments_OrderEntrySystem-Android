package neit.lab1fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;
import static java.lang.Integer.parseInt;
import static neit.lab1fragments.MainActivity.CandyOrders;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    EditText txtFirstName; // first name
    EditText txtLastName; // last name
    Spinner ddTypeOfChoc; // dropdown type of chocolate
    EditText txtNumberOfBars; // number of bars
    RadioButton rbNormalShipment; // normal shipment
    RadioButton rbExpited; // expidited shipment
    Boolean shipment; // true or false for radio button
    TextView txtResults; // text results
    Button btnGetResults; // get results button
    View view;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);

        txtFirstName = view.findViewById(R.id.txtFirstName); // first name
        txtLastName = view.findViewById(R.id.txtLastName); // last name
        ddTypeOfChoc = view.findViewById(R.id.ddTypeOfChoc); // dropdown
        rbNormalShipment = view.findViewById(R.id.rbNormalShipment); // normal shipment
        rbExpited = view.findViewById(R.id.rbExpited); // expidited
        txtNumberOfBars = view.findViewById(R.id.txtNumberOfBars); // number of bars
        txtResults = view.findViewById(R.id.txtResults);// results
        btnGetResults = view.findViewById(R.id.btnGetResults); // save button

        btnGetResults.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String TypeOfChoc = ddTypeOfChoc.getSelectedItem().toString(); // dropdown
                String bars = txtNumberOfBars.getText().toString();
                int NumberOfBars = parseInt(bars);

                Order purchaseOrder = new Order();
                purchaseOrder.setFirstName(firstName);
                purchaseOrder.setLastName(lastName);
                purchaseOrder.setTypeOfChocolate(TypeOfChoc);
                purchaseOrder.setBars(NumberOfBars);
                if (rbNormalShipment.isChecked()) {
                    shipment = true;

                } else {
                    shipment = false;
                }
                purchaseOrder.setShippintType(shipment);
                CandyOrders.add(purchaseOrder);
                Integer result = CandyOrders.size();
                txtResults.setText("Orders " + result.toString());

                onButtonPressed(Uri.parse("Order Added, Current Orders: " + CandyOrders.size()));

                txtFirstName.setText("");
                txtLastName.setText("");
                rbNormalShipment.setChecked(true);
                ddTypeOfChoc.setSelection(0);
                txtNumberOfBars.setText("");
                txtFirstName.requestFocus(); //
            }



        });

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    // when I click an order in the list, set everything from public void onListFragmentInteraction(Order item) in MainActivity
    public void LoadFragmentData(String fname, String lname, String choc, int bars, boolean ship){

        txtFirstName.setText(fname);
        txtLastName.setText(lname);
        ddTypeOfChoc.setSelection(((ArrayAdapter<String>) ddTypeOfChoc.getAdapter()).getPosition(choc));
        txtNumberOfBars.setText(String.valueOf(bars).toString());
        if (ship == true) {
            rbNormalShipment.setChecked(true);
        } else {
            rbExpited.setChecked(true);
        }

    }
}
