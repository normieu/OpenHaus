package openhaus.common.actions;
import java.io.Serializable;
import java.util.ArrayList;

	

	public class UpdateListAction extends Action implements Serializable {

		private static final long serialVersionUID = 1L;
		private ArrayList<String> list;

		public UpdateListAction() {
			super();
		}
		
		public UpdateListAction(String source, String destination, String type, ArrayList<String> list) {
			super(source, destination, type);
			setList(list);
		}

		public void setList(ArrayList<String> list) {
			this.list = list;
		}

		public ArrayList<String> getList() {
			return list;
		}

		

	}
