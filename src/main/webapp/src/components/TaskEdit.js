import React, { Component } from 'react';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {Link, withRouter} from 'react-router-dom';
import {PathStore} from "./PathStore";

class TaskEdit extends Component {
    emptyTask = {
        heading: '',
        text: '',
        date_added: '',
        edit_date: '',
        status: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            task: this.emptyTask,
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const body = await (await fetch(PathStore.globalPath +`/task/${this.props.match.params.id}`)).json();
            this.setState({task: body});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let task = {...this.state.task};
        task[name] = value;
        this.setState({task});
    }

    async handleSubmit(event) {
        console.log(event);
        event.preventDefault();
        const { task } = this.state;

        await fetch((task.id) ? '/upd/' + task.id : '/task/new/',{
            method: (task.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(task),
        });
        //(task.id) ? this.props.history.push('/upd/' + task.id) : this.props.history.push('/');
        this.props.history.push('/');
    }

    render() {
        const { task } = this.state;
        console.log(task);
        const title = <h2>{task.id ? 'Внести изменения' : 'Добавить задачу'}</h2>;
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="heading">Заголовок</Label>
                        <Input type="text" name="heading" id="heading" value={task.heading || ''}
                               onChange={this.handleChange} autoComplete="heading"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="text">Текст</Label>
                        <Input type="text" name="text" id="text" value={task.text || ''}
                               onChange={this.handleChange} autoComplete="text"/>
                    </FormGroup>
                    <div className="row">
                        <FormGroup className="col-md-5 mb-3">
                            <Label for="date_added">Дата добавления</Label>
                            <Input type="text" name="date_added" id="date_added" value={task.date_added || ''}
                                   onChange={this.handleChange} autoComplete="date_added"/>
                        </FormGroup>
                        <FormGroup className="col-md-5 mb-3">
                            <Label for="edit_date">Дата редактирования</Label>
                            <Input type="text" name="edit_date" id="edit_date" value={task.edit_date || ''}
                                   onChange={this.handleChange} autoComplete="edit_date"/>
                        </FormGroup>
                        <FormGroup className="col-md-5 mb-3">
                            <Label for="status">Статус</Label>
                            <select className="custom-select" name="status" id="status" onChange={this.handleChange} >
                                <option selected type="text" autoComplete="status">{task.status || ''}</option>
                                <option value="done">сделано</option>
                                <option value="new">новое</option>
                            </select>
                        </FormGroup>
                    </div>
                    <FormGroup>
                        <Button color="primary" type="submit">Сохранить</Button>{' '}
                        <Button color="secondary" tag={Link} to={'/'}>Отмена</Button>{' '}
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

}

export default withRouter(TaskEdit);