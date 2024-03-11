import { createAction } from '@reduxjs/toolkit';
import ReducerActions from '../ReducerActions';

export interface TogglePermissionPayload {
  permission: string;
}

const toggleResource = createAction<TogglePermissionPayload>(ReducerActions.TOGGLE_RESOURCE);

export default toggleResource;