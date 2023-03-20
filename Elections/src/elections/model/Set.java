package elections.model;
import java.io.Serializable;
import java.util.Vector;

public class Set <E> implements Serializable {

	private Vector <E> allCitizens;

	public Set() {
		this.allCitizens = new Vector <E>();

	}

	public Vector<E> getAllCitizens() {
		return allCitizens;
	}
	public E get(int spot) {
		return allCitizens.get(spot);
	}

	public int size() {
		return allCitizens.size();
	}
	
	public boolean addCitizen(E citizen) throws RegisteredCitizenException {
		if((allCitizens.contains(citizen) && citizen instanceof Citizen) == false) {
			allCitizens.add(citizen);
			return true;
		}
		else {
			if(allCitizens.contains(citizen)) {
				System.out.println("Citizen could not be added");
				return false;
			}
			else
				throw new RegisteredCitizenException();
		}

	}
	@Override
	public String toString() {
		return "Set [allCitizens=" + allCitizens + "]";
	}

	

}
