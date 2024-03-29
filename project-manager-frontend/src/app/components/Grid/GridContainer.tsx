import * as React from "react";
// nodejs library to set properties for components
import * as PropTypes from 'prop-types'
// @material-ui/core components
import {makeStyles} from "@material-ui/core/styles";
import Grid from "@material-ui/core/Grid";

const styles = {
  grid: {
    margin: "0 -15px !important",
    width: "unset"
  }
};

const useStyles = makeStyles(styles as any);

export function GridContainer(props: any) {
  const classes = useStyles({} as any);
  const {children, ...rest} = props;
  return (
    <Grid container {...rest} className={classes.grid}>
      {children}
    </Grid>
  );
}

GridContainer.propTypes = {
  children: PropTypes.node
};

export default GridContainer;
