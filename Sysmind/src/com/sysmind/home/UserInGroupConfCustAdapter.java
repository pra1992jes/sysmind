package com.sysmind.home;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.csipsimple.R;

public class UserInGroupConfCustAdapter extends BaseAdapter implements OnClickListener {
	private Activity activity;
    private ArrayList<UserInGroupConfSampleBean> data;
    private static LayoutInflater inflater=null;
    public Resources res;
    Intent in;
    int i=0;
    String uri=LoginPage.uri;
   	String uri1 = "conferenceRequestSysmind?";
   	String xml;
 public UserInGroupConfCustAdapter(Activity list, ArrayList<UserInGroupConfSampleBean> d) {
        
        /********** Take passed values **********/
         activity = list;
         data=d;
     //    res = resLocal;
      
         /***********  Layout inflator to call external xml layout () ***********/
          inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
 }
 
	@Override
	public int getCount() {
		 if(data.size()<=0)
	            return 1;
	        return data.size();
	}
	
	@Override
	public Object getItem(int arg0) {
        return arg0;

	}

	@Override
	public long getItemId(int arg0) {
	     return arg0;
	}
	  public static class ViewHolder{
          
		  Button b2;
		  TextView tv;
      }

	
	  @Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			View vi = arg1;
			 ViewHolder holder;
	       
	         
	        if(arg1==null){
	             
	            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
	            vi = inflater.inflate(R.layout.user_in_group_conf_listview, null);
	            holder = new ViewHolder();
	             
	            /****** View Holder Object to contain tabitem.xml file elements ******/
	holder.tv=(TextView) vi.findViewById(R.id.addGroupConf_button);
	          // holder.bgroup=(Button) vi.findViewById(R.id.addGroupbtn);
	          holder. b2=(Button) vi.findViewById(R.id.addGroupConf_conf_button);
	             
	           /************  Set holder with LayoutInflater ************/
	           vi.setTag( holder );
	        }
	        else 
	            holder=(ViewHolder)vi.getTag();
	         
	        if(data.size()<=0)
	        {
	           Toast.makeText(activity, "no data", 1000).show();
	             
	        }
	        else
	        {
	            /***** Get each Model object from Arraylist ********/
	            
	             
	            /************  Set Model values in Holder elements ***********/

	             holder.tv.setText((data.get(position).getValue()));
	         //    holder.b2.setText((data.get(position).getValue()));
	              
	             /******** Set Item Click Listner for LayoutInflater for each row *******/
	            // holder.bgroup.setOnClickListener(new OnItemClickListener(position));
	             holder.b2.setOnClickListener(new OnItemClickListener(position));
	             holder.tv.setOnClickListener(new OnItemClickListener(position));
	           
	        }
	       
	        return vi;
	    }
	  @Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}
	  private class OnItemClickListener  implements OnClickListener{           
	         private int mPosition;
	          
	         OnItemClickListener(int position){
	              mPosition = position;
	         }
	         
	         @Override
	 		public void onClick(View v) {
	 			
//	        	 TextView txt = (TextView)v.findViewById(R.id.adduser_button);
//	 				String txt1 = txt.getText().toString();
	 			switch(v.getId())
	 			{
	 			case R.id.addGroupConf_conf_button:
	 			
	 				Button txtu = (Button)v.findViewById(R.id.addGroupConf_conf_button);
	 			//	Toast.makeText(activity.getApplicationContext(), "Welcum +++**",Toast.LENGTH_LONG).show();				
//	 				String finalUrl = uri + uri1 + "confbridgeExtension="+LoginPage.myExtension+"&joinUser="+data.get(mPosition).getValue();
	 				String finalUrl = uri + uri1;
	 				XMLParser parser = new XMLParser();
					parser.params.add(new BasicNameValuePair("confbridgeExtension", LoginPage.myExtension));
					parser.params.add(new BasicNameValuePair("joinUser", data.get(mPosition).getValue()));			
//					Toast.makeText(activity.getApplicationContext(), "Welcum to url=" + finalUrl,
//							Toast.LENGTH_LONG).show();
					
					xml = parser.getXmlFromUrl(finalUrl);
					Toast.makeText(activity.getApplicationContext(), "xml in add grp=" + xml,
							Toast.LENGTH_LONG).show();
					
					in = new Intent(activity.getApplicationContext(), com.sysmind.home.UserGroup.class);
					in.putExtra("FunalUrl", finalUrl);
					activity.startActivity(in);
					
	 				break;
	 				
	 			case R.id.addGroupConf_button:
	 				TextView txt = (TextView)v.findViewById(R.id.addGroupConf_button);
	 				String txt1 = txt.getText().toString();
	 				in=new Intent(activity.getApplicationContext(),com.sysmind.home.Employee_Profile_Conf_Page.class);
//	 				in.putExtra("UserName", txt1);
	 				
	 				LoginPage.toEdit.putBoolean("IS_LOGIN", true);
					LoginPage.toEdit.putString("UserName", txt1); 
					LoginPage.toEdit.putString("joinUser", data.get(mPosition).getValue()); 
				
					LoginPage.toEdit.commit(); 
					
					
//	 				activity.startActivity(in);
	 				activity.startActivity(in);
	 				break;
	 				
	 			}
	 			
	 		}
	  }
}
