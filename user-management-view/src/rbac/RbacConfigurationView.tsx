import {
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow
} from '@mui/material';
import React from 'react';
import ResourceConfiguration from './resource/ResourceConfiguration';
import RbacState from '../redux/rbac/state/RbacState';
import './RbacConfigurationView.css'; 


interface Props {
  rbac: RbacState;
  roles: string[];
  resources: string[];
  handleSaveClick: () => void
}

const RbacConfigurationView: React.FC<Props> = ({ rbac, roles, resources, handleSaveClick }) => {
  return (
    <React.Fragment>
      <TableContainer component={Paper} className="table-container">
        <Table className="custom-table">
          <TableHead>
            <TableRow>
              <TableCell>Resources/Actions</TableCell>
              {roles.map((role) => (
                <TableCell key={role}>{role}</TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {resources.map((resource) => (
              <ResourceConfiguration key={resource} rbac={rbac} roles={roles} resource={resource} />
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <div className="space-after-table"></div>
      <Button size="large" variant="contained" onClick={() => handleSaveClick()}>Save Configuration</Button>
    </React.Fragment>

  );
};

export default RbacConfigurationView;