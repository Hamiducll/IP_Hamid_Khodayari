<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${param.page}</title>
    <style>
        /* Style the navigation bar */
        nav {
            display: flex;
            width: 100%;
            background-color: #555;
            overflow: auto;
        }

        /* Navbar links */
        nav a {
            float: left;
            text-align: center;
            padding: 12px;
            color: white;
            text-decoration: none;
            font-size: 17px;
        }

        /* Navbar links on mouse-over */
        nav a:hover {
            background-color: #000;
        }

        /* Current/active navbar link */
        .active {
            background-color: #4CAF50;
        }

        /* Add responsiveness - will automatically display the navbar vertically instead of horizontally on screens less than 500 pixels */
        @media screen and (max-width: 500px) {
            nav a {
                float: none;
                display: block;
            }
        }

        footer {
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
            background-color: #555;
            color: white;
            text-align: center;
        }
    </style>
</head>