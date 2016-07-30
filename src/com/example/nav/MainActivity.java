package com.example.nav;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private ListView mDrawerList;
	private ArrayAdapter<String> mAdapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private String mActivityTitle;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeButtonEnabled(true);
        
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle=getTitle().toString();
        mDrawerList=(ListView) findViewById(R.id.navList);
        addDrawerItems();
        setupDrawer();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				
				Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
				
			}
		});
    }

    private void addDrawerItems()
    {
    	String[] osArray={"Home","About","Contact"};
    	mAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,osArray);
    	mDrawerList.setAdapter(mAdapter);
    }
    
    public void setupDrawer()
    {
    	mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open,R.string.drawer_close)
    	{
    		
    		public void onDrawerOpened(View drawerView) 
    		{
    			 super.onDrawerOpened(drawerView);
    			    getSupportActionBar().setTitle("Navigation!");
    			    invalidateOptionsMenu(); 
    	    }

    	    public void onDrawerClosed(View drawerView)
    	    {
    	    	 super.onDrawerClosed(drawerView);
    	    	    getSupportActionBar().setTitle(mActivityTitle);
    	    	    invalidateOptionsMenu(); 
    	    }
    	};
    	mDrawerToggle.setDrawerIndicatorEnabled(true);
    	mDrawerLayout.addDrawerListener(mDrawerToggle);
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.navigate)
        {
        	startActivity(new Intent(this,SubActivity.class));
        }
        if(mDrawerToggle.onOptionsItemSelected(item))
        	return true;

        return super.onOptionsItemSelected(item);
    }
   public void onPostCreate(Bundle savedInstanceState)
   {
	   super.onPostCreate(savedInstanceState);
	   mDrawerToggle.syncState();
   }
   @Override
   public void onConfigurationChanged(Configuration newConfig) {
       super.onConfigurationChanged(newConfig);
       mDrawerToggle.onConfigurationChanged(newConfig);
   }
}