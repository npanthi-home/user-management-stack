import { createAction } from '@reduxjs/toolkit';
import ReducerActions from '../ReducerActions';

export interface TogglePermissionPayload {
  permission: string;
}

const toggleAction = createAction<TogglePermissionPayload>(ReducerActions.TOGGLE_ACTION);

export default toggleAction;