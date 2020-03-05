import React, { Component } from 'react';
import '../App.css';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';
import {PathStore} from "./PathStore";

class Home extends Component {
    state = {
        isLoading: true,
        task: [],
        /*heading: '',
        text: '',
        date_added: '',
        edit_date: '',
        status: ''*/
    };

    constructor(props) {
        super(props);

        this.remove = this.remove.bind(this);
    }

    async componentDidMount() {
        const body = await (await fetch(PathStore.globalPath + '/task-list')).json();
        this.setState({ task: body, isLoading: false });
    }

    async remove(id) {
        await fetch(PathStore.globalPath + `/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedTask = [...this.state.task].filter(i => i.id !== id);
            this.setState({task: updatedTask});
        });
    }

    render() {
        const { task, isLoading } = this.state;
        if (isLoading) {
            return <p>Loading...</p>;
        }

        const taskList = task.map(t => {
            return <tr key={t.id}>
                <td style={{whiteSpace: 'nowrap'}}> <p key={t.id}>
                    {t.heading}</p>
                </td>
                <td>
                    {t.text}
                </td>
                <td>
                    {t.date_added}
                </td>
                <td>
                    {t.edit_date}
                </td>
                <td>
                    {t.status}
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/task/" + t.id}>Изменить</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(t.id)}>Удалить</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to={"/task/new/"}>Добавить задачу</Button>
                    </div>

                    <h3>Список задач:</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="15%">Заголовок</th>
                            <th width="30%">Текст</th>
                            <th width="10%">Дата добавления</th>
                            <th width="10%">Дата редактирования</th>
                            <th width="10%">Статус</th>
                        </tr>
                        </thead>
                        <tbody>
                        {taskList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Home;