package tutorlink.component;

public class Assignment extends Component {
    public Assignment(String name, double maxScore, double weight) {
        super(name, maxScore, weight);
    }

    @Override
    public String toString() {
        return "Assignment " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Assignment)) {
            return false;
        }
        return super.equals(obj);
    }
}
