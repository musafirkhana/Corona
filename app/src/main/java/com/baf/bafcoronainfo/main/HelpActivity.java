package com.baf.bafcoronainfo.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.holder.HelpHolder;
import com.baf.bafcoronainfo.model.HelpModel;
import com.baf.bafcoronainfo.parser.HelplistParser;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class HelpActivity extends Activity  {
    private Context mContext;
    List<String> baseList = new ArrayList<String>();
    private String[] respones_results;
    String titleText;

    private String text;
    private HelpAdapter helpAdapter;
    public EditText mobile_no_search;
    private ListView profile_list;
    private TextView topbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help);
        mContext = this;
        titleText=getIntent().getStringExtra("title");
        baseList.add("Bangabandhu");
        baseList.add("BSR");
        baseList.add("PKP");
        baseList.add("MTR");
        baseList.add("ZHR");
        baseList.add("Sheikh Hasina");

        loadAssetData("help.txt");
        initUI();

    }

    private void initUI() {
        topbar=(TextView)findViewById(R.id.topbar);
        mobile_no_search = (EditText) findViewById(R.id.mobile_no_search);
        profile_list = (ListView) findViewById(R.id.profile_list);
        helpAdapter = new HelpAdapter(this);
        mobile_no_search.setFocusableInTouchMode(true);
        profile_list.setAdapter(helpAdapter);
        topbar.setText(titleText);
        profile_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                HelpModel query= helpAdapter.planetList.get(position);
               /* Intent intent = new Intent(mContext, ProfileActivity.class);
                intent.putExtra("name",""+query.getName());
                intent.putExtra("appoinment",""+query.getAppoinment());
                intent.putExtra("rank",""+query.getRank());
                intent.putExtra("branch",""+query.getBranch());
                intent.putExtra("dob",""+query.getDob());
                intent.putExtra("blood",""+query.getBlood_group());
                intent.putExtra("mobile",""+query.getMobile());
                intent.putExtra("email",""+query.getEmail());
                intent.putExtra("profile",""+query.getMobile());
//            toastUtil.appSuccessMsg(mContext,query.getName()+""+position);
                startActivity(intent);*/

            }
        });
        // TextFilter
        profile_list.setTextFilterEnabled(true);

        mobile_no_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (count < before) {
                    // We're deleting char so we need to reset the adapter data
                    helpAdapter.resetData();
                }

                helpAdapter.getFilter().filter(s.toString());

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
        mobile_no_search.clearFocus();
    }

    public void BACK(View v) {
        this.finish();

    }

    private void loadAssetData(String fileName) {
        try {
            InputStream is = getAssets().open(fileName);

            // We guarantee that the available method returns the total
            // size of the asset... of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            text = new String(buffer);
            Log.i("Hello ", fileName+text);

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

        new LoadAsyncTask().execute(text);

    }



    /******************************
     * Load Data From Asset Folder
     ********************************/

    public class LoadAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... aurl) {

            respones_results = aurl;
            try {
                if (HelplistParser.connect(getApplicationContext(),aurl[0]));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
        }
        @SuppressLint("NewApi")
        protected void onPostExecute(String getResult) {
            //progDialogConfirm.dismiss();
            //setData();
        }
    }

    class HelpAdapter extends ArrayAdapter<HelpModel> {
        Context context;
        private File sdCard = Environment.getExternalStorageDirectory();
        public String response;
        public boolean asyncCheck = false;
        public String ContentCode;
        public String mobileNo;
        File pathName = null;

        private Filter planetFilter;
        private List<HelpModel> origPlanetList;
        private List<HelpModel> planetList;

        public HelpAdapter(Context context) {
            super(context, R.layout.row_help, HelpHolder.getHelplist());
            this.context = context;
            this.context = context;
            this.planetList = HelpHolder.getHelplist();
            this.origPlanetList = HelpHolder.getHelplist();

        }

        public int getCount() {
            return planetList.size();
        }

        public HelpModel getItem(int position) {
            return planetList.get(position);
        }

        public long getItemId(int position) {
            return planetList.get(position).hashCode();
        }

        class ViewHolder {

            private TextView tv_appt_name;
            private TextView tv_mobileno;
            private RelativeLayout call_1;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row_help, null);
                holder = new ViewHolder();

                holder.tv_appt_name = (TextView) v.findViewById(R.id.tv_appt_name);
                holder.tv_mobileno = (TextView) v.findViewById(R.id.tv_mobileno);
                holder.call_1=(RelativeLayout)v.findViewById(R.id.call_1);

                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            if (position < HelpHolder.getHelplist().size()) {
                final HelpModel query = planetList.get(position);
                holder.tv_appt_name.setText(query.getAppoinment_name());
                holder.tv_mobileno.setText(query.getMobile_no());
                holder.call_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",
                                query.getMobile_no(), null));
                        startActivity(intent);
                    }
                });


            }

            return v;
        }

        public void resetData() {
            planetList = origPlanetList;
        }
        /*
         * We create our filter
         */

        @Override
        public Filter getFilter() {
            if (planetFilter == null)
                planetFilter = new PlanetFilter();

            return planetFilter;
        }

        private class PlanetFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // We implement here the filter logic
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = origPlanetList;
                    results.count = origPlanetList.size();
                } else {
                    // We perform filtering operation
                    List<HelpModel> nPlanetList = new ArrayList<HelpModel>();
                    for (HelpModel p : planetList) {

                        if (p.getBase().toUpperCase().startsWith(constraint.toString().toUpperCase())||
                                p.getAppoinment_name().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                            nPlanetList.add(p);
                        }
                        Timber.i("Filter Places    " + p.getBase().toLowerCase());
                        Timber.d("Filter constraint  " + constraint.toString().toLowerCase());

                    }

                    results.values = nPlanetList;
                    results.count = nPlanetList.size();

                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                // Now we have to inform the adapter about the new list filtered
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    planetList = (List<HelpModel>) results.values;
                    notifyDataSetChanged();
                }

            }

        }
    }



}
