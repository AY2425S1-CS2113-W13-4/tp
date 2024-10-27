package tutorlink.component;

public class ClassParticipation extends Component {
    public ClassParticipation(String name, double maxScore, double weight) {
        super(name, maxScore, weight);
    }

    @Override
    public String toString() {
        return "ClassParticipation " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClassParticipation)) {
            return false;
        }
        return super.equals(obj);
    }
}
