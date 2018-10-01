

public class Group7Bank {

    public static void main(String args[]){



       Input frame = new Input();
       frame.setVisible(true);


      new java.util.Timer().schedule(
   	        new java.util.TimerTask() {
   	            @Override
   	            public void run() {
   	            	frame.setVisible(false);
   	            }
   	        },
   	        120000
   	);
    }
}

