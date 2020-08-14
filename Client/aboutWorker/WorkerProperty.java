package aboutWorker;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class WorkerProperty {

    private final SimpleIntegerProperty idProperty;
    private final SimpleStringProperty nameProperty;
    private final SimpleIntegerProperty coordinateXProperty;
    private final SimpleIntegerProperty coordinateYProperty;
    private final SimpleStringProperty creationDateProperty;
    private final SimpleLongProperty salaryProperty;
    private final SimpleStringProperty startDateProperty;
    private final SimpleStringProperty endDateProperty;
    private final SimpleStringProperty positionProperty;
    private final SimpleStringProperty organizationTypeProperty;
    private final SimpleIntegerProperty employeesCountProperty;
    private final SimpleStringProperty ownerProperty;

    public WorkerProperty(Worker worker){
        this.idProperty = new SimpleIntegerProperty(worker.getId());
        this.nameProperty = new SimpleStringProperty(worker.getName());
        this.coordinateXProperty = new SimpleIntegerProperty(worker.getCoordinates().getX());
        this.coordinateYProperty = new SimpleIntegerProperty(worker.getCoordinates().getY());
        this.creationDateProperty = new SimpleStringProperty(String.valueOf(worker.getCreationDate()));
        this.salaryProperty = new SimpleLongProperty(worker.getSalary());
        this.startDateProperty = new SimpleStringProperty(String.valueOf(worker.getStartDate()));
        this.endDateProperty = new SimpleStringProperty(String.valueOf(worker.getEndDate()));
        this.positionProperty = new SimpleStringProperty(String.valueOf(worker.getPosition()));
        this.organizationTypeProperty = new SimpleStringProperty(String.valueOf(worker.getOrganization().getType()));
        this.employeesCountProperty = new SimpleIntegerProperty(worker.getOrganization().getEmployeesCount());
        this.ownerProperty = new SimpleStringProperty(worker.getOwner());
    }

    public SimpleIntegerProperty getIdProperty(){
        return idProperty;
    }
    public void setIdProperty(Integer id){
        idProperty.set(id);
    }

    public SimpleStringProperty getNameProperty(){
        return nameProperty;
    }
    public void setNameProperty(String name){
        nameProperty.set(name);
    }

    public SimpleIntegerProperty getCoordinateXProperty(){
        return coordinateXProperty;
    }
    public void setCoordinateXProperty(Integer coordinateX){
        coordinateXProperty.set(coordinateX);
    }

    public SimpleIntegerProperty getCoordinateYProperty(){
        return coordinateYProperty;
    }
    public void setCoordinateYProperty(Integer coordinateY){
        coordinateYProperty.set(coordinateY);
    }

    public SimpleStringProperty getCreationDateProperty(){
        return creationDateProperty;
    }
    public void setCreationDateProperty(String creationDate){
        creationDateProperty.set(creationDate);
    }

    public SimpleLongProperty getSalaryProperty(){
        return salaryProperty;
    }
    public void setCoordinateYProperty(Long salary){
        salaryProperty.set(salary);
    }

    public SimpleStringProperty getStartDateProperty(){
        return startDateProperty;
    }
    public void setStartDateProperty(String startDate){
        startDateProperty.set(startDate);
    }

    public SimpleStringProperty getEndDateProperty(){
        return endDateProperty;
    }
    public void setEndDateProperty(String endDate){
        endDateProperty.set(endDate);
    }

    public SimpleStringProperty getPositionProperty(){
        return positionProperty;
    }
    public void setPositionProperty(String position){
        positionProperty.set(position);
    }

    public SimpleStringProperty getOrganizationTypeProperty(){
        return organizationTypeProperty;
    }
    public void setOrganizationTypeProperty(String organizationType){
        organizationTypeProperty.set(organizationType);
    }

    public SimpleIntegerProperty getEmployeesCountProperty(){
        return employeesCountProperty;
    }
    public void setEmployeesCountProperty(Integer employeesCount){
        employeesCountProperty.set(employeesCount);
    }

    public SimpleStringProperty getOwnerProperty(){
        return ownerProperty;
    }
    public void setOwnerProperty(String owner){
        ownerProperty.set(owner);
    }
}
